package config.dev;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.test.context.ActiveProfiles;
import redis.clients.jedis.JedisPoolConfig;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableCaching
@ActiveProfiles("dev")
public class CacheConfig_DEV extends CachingConfigurerSupport {
	
	@Value("${spring.redis.cluster:}")
	List<String> nodes = new ArrayList<String>();
	
	@Value("${spring.redis.master:127.0.0.1}")
	private String masterIp;
	
	@Value("${spring.redis.port:6379}")
	private int port;
	
	@Bean
	public JedisConnectionFactory connectionFactory() {
		
		//JedisConnectionFactory factory = new JedisConnectionFactory(new RedisClusterConfiguration(nodes));
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setHostName(masterIp);
		factory.setPort(port);
		JedisPoolConfig poolConfig = factory.getPoolConfig();
		poolConfig.setMaxTotal(180);
		poolConfig.setMaxWaitMillis(10000);
		return factory;
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory cf) {
		
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
		template.setConnectionFactory(cf);
		return template;
	}

	@Bean
	public CacheManager cacheManager(RedisTemplate<String, Object> template) {
		RedisCacheManager cM = new RedisCacheManager(template);
		cM.setDefaultExpiration(600);
		return cM;
	}

	@Bean
	public KeyGenerator keyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object o, Method method, Object... params) {
				StringBuilder sb = new StringBuilder();
				sb.append(o.getClass().getName());
				sb.append(method.getName());
				for (Object param : params) {
					if(param != null)
					sb.append(param.toString());
				}
				return sb.toString();
			}
		};

	}
	
}
