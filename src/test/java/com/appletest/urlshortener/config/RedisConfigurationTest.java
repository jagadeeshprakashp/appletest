package com.appletest.urlshortener.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.connection.RedisConnectionFactory;

@ExtendWith(MockitoExtension.class)
public class RedisConfigurationTest {

    @InjectMocks
    RedisConfiguration redisConfiguration;

    @Mock
    RedisConnectionFactory redisConnectionFactory;

    @Mock
    ObjectMapper objectMapper;

    @Test
    void testRedisTemplate(){
        redisConfiguration.redisTemplate();
    }
}
