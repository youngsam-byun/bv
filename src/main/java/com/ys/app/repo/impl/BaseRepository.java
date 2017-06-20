package com.ys.app.repo.impl;

import com.ys.app.model.mapper.BaseRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import javax.sql.DataSource;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by byun.ys on 4/11/2017.
 */
@SuppressWarnings(value = {"unchecked","WeakerAccess"})
public abstract class BaseRepository<T> {
    
    private static  String KEY_T = "T";
    private static  int INDEX = 0;
    private static  String TABLE = "table";
    private static  String COLUMN_NAME = "columnName";
    private static  String COLUMN_VALUE = "value";
    private static  String ID = "id";
    private static  String KEYWORD = "keyword";
    private static  String G_GET_LIST_ALL = "G_getListAll";
    private static  String G_READ_BY_COLUMN = "G_readByColumn";
    private static  String G_READ_BY_ID = "G_readById";
    private static  String G_DELETE_BY_ID = "G_deleteById";
    private static  String G_DELETE_BY_UPDATE_ID = "G_deleteByUpdateId";
    private static  String G_DELETE_BY_KEY_WORD = "G_deleteByKeyWord";

    protected String table;
    protected DataSource dataSource;
    protected BaseRowMapper<T> baseRowMapper;

    private SimpleJdbcCall simpleJdbcCall;
    private String storedProcedure;

    protected void setTable(String table) {
        this.table = table;
    }

    protected  int create(String storedProcedure, T t) {
        SqlParameterSource sqlParameterSource = baseRowMapper.createParameterSource(t).addValue(TABLE, table);
        return executeForInt(storedProcedure, sqlParameterSource);
    }

    protected  T readbyId(int id) {
        storedProcedure = G_READ_BY_ID;
        try {

            SqlParameterSource sqlParameterSource = createParameters(new SimpleEntry<>("id", id));
            return execute(storedProcedure, sqlParameterSource);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            throw e;
        }
    }

    protected  T readByColumn(String columnName, String value) {

        storedProcedure = G_READ_BY_COLUMN;
        try {
            SqlParameterSource sqlParameterSource = createParameters(new SimpleEntry<>(COLUMN_NAME, columnName), new SimpleEntry<>(COLUMN_VALUE, value));
            return execute(storedProcedure, sqlParameterSource);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            throw e;
        }
    }

    protected   T readSimple(String procedureName, SimpleEntry<String, Object>... requiredParameters){
        try {
            SqlParameterSource sqlParameterSource = createParameters(requiredParameters);
            return execute(procedureName, sqlParameterSource);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            throw e;
        }

    }

    protected  void executeSimple(String procedureName, SimpleEntry<String, Object>... requiredParameters) {
        SqlParameterSource sqlParameterSource = createParameters(requiredParameters);
        simpleJdbcCall= new SimpleJdbcCall(dataSource).withProcedureName(procedureName);
        simpleJdbcCall.execute(sqlParameterSource);
    }

    protected  <E> E executeStoredProcedure(String procedureName, SimpleEntry<String, Object>... requiredParameters) {
        SqlParameterSource sqlParameterSource = createParameters(requiredParameters);
        return (E) execute(procedureName, sqlParameterSource);
    }

    protected  int executeStoredProcedureForObject(String procedureName, SimpleEntry<String, Object>... requiredParameters) {
        SqlParameterSource sqlParameterSource = createParameters(requiredParameters);
        return  executeForInt(procedureName, sqlParameterSource);
    }

    protected  int update(String storedProcedure, T t) {
        SqlParameterSource sqlParameterSource = baseRowMapper.updateParameterSource(t).addValue(TABLE, table);
        return executeForInt(storedProcedure, sqlParameterSource);
    }

    protected  int deleteById(int id) {
        SqlParameterSource sqlParameterSource = createParameters(new SimpleEntry<>(ID, id));
        return executeForInt(G_DELETE_BY_ID, sqlParameterSource);
    }

    protected  int deleteByUpdateId(int id) {
        SqlParameterSource sqlParameterSource = createParameters(new SimpleEntry<>(ID, id));
        return executeForInt(G_DELETE_BY_UPDATE_ID, sqlParameterSource);
    }

    protected  int deleteBySearch(String keyword) {
        SqlParameterSource sqlParameterSource = createParameters(new SimpleEntry<>(KEYWORD, keyword));
        return executeForInt(G_DELETE_BY_KEY_WORD, sqlParameterSource);
    }


    protected  List<T> getList(String storedProcedure, SimpleEntry<String, Object>... requiredParameters) {

        try {
            SqlParameterSource sqlParameterSource = createParameters(requiredParameters);
            simpleJdbcCall= new SimpleJdbcCall(dataSource).withProcedureName(storedProcedure).returningResultSet(KEY_T, baseRowMapper);
            Map<String, Object> resultHashMap = simpleJdbcCall.execute(sqlParameterSource);

            List<T> resultList = (ArrayList<T>) resultHashMap.get(KEY_T);

            if (resultList != null && resultList.size() > 0)
                return resultList;
            else
                return null;

        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            throw e;
        }
    }


    public  int getTotal(String storedProcedure, SimpleEntry<String, Object>... requiredParameters) {

        try {
            SqlParameterSource sqlParameterSource = createParameters(requiredParameters);
            return executeForInt(storedProcedure, sqlParameterSource);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            throw e;
        }

    }

    protected    List<T> getListAll(){
        simpleJdbcCall=new SimpleJdbcCall(dataSource).withProcedureName(G_GET_LIST_ALL).returningResultSet(KEY_T,baseRowMapper);
        Map<String, Object> resultHashMap = simpleJdbcCall.execute(new MapSqlParameterSource().addValue(TABLE,table));

        List<T> resultList = (ArrayList<T>) resultHashMap.get(KEY_T);

        if (resultList != null && resultList.size() > 0)
            return resultList;
        else
            return null;
    }

    private SqlParameterSource createParameters(Map<String, Object> hashMap, SimpleEntry<String, Object>... requiredParameters) {
        hashMap.put(TABLE, table);
        for (SimpleEntry<String, Object> entry : requiredParameters) {
            String key = entry.getKey();
            Object value = entry.getValue();

            hashMap.put(key, value);
        }

        return new MapSqlParameterSource(hashMap);
    }


    private SqlParameterSource createParameters(SimpleEntry<String, Object>... requiredParameters) {

        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put(TABLE, table);

        for (SimpleEntry<String, Object> entry : requiredParameters) {
            String key = entry.getKey();
            Object value = entry.getValue();

            hashMap.put(key, value);
        }

        return new MapSqlParameterSource(hashMap);
    }

    private T execute(String procedureName, SqlParameterSource sqlParameterSource) {

        simpleJdbcCall= new SimpleJdbcCall(dataSource).withProcedureName(procedureName).returningResultSet(KEY_T, baseRowMapper);
        Map<String, Object> resultHashMap = simpleJdbcCall.execute(sqlParameterSource);
        List<T> resultList = (ArrayList<T>) resultHashMap.get(KEY_T);

        if (resultList != null && resultList.size() > 0)
            return resultList.get(INDEX);
        else
            return null;
    }

    private int executeForInt(String storedProcedure, SqlParameterSource sqlParameterSource) {
        simpleJdbcCall= new SimpleJdbcCall(dataSource).withProcedureName(storedProcedure);
        return simpleJdbcCall.executeObject(int.class, sqlParameterSource);
    }
}
