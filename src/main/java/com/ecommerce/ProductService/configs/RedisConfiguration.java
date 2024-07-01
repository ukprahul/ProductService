package com.ecommerce.ProductService.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfiguration {
    @Bean
    public RedisTemplate<String,Object> createRedisTemplate(RedisConnectionFactory connectionFactory){
        RedisTemplate<String,Object> template =  new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        return template;
    }
}
