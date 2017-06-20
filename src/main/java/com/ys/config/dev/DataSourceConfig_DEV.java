package com.ys.config.dev;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by byun.ys on 4/12/2017.
 */



@Configuration
@PropertySource("classpath:conf/db/db_dev.properties")
@EnableTransactionManagement
public class DataSourceConfig_DEV {

    @Value("${jdbc.driverClassName}")
    private  String driverClassName;

    @Value("${jdbc.url}")
    private  String url;

    @Value("${jdbc.username}")
    private  String username;

    @Value("${jdbc.password}")
    private  String password;

    @Value("${jdbc.initialSize}")
    private  int intialSize;

    @Value("${jdbc.maxTotal}")
    private  int maxTotal;

   @Bean
    public DataSource dataSource() {

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setInitialSize(intialSize);
        dataSource.setMaxTotal(maxTotal);

        return dataSource;
    }


    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }


}
