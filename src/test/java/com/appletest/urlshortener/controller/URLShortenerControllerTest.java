package com.appletest.urlshortener.controller;

import com.appletest.urlshortener.model.UrlDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class URLShortenerControllerTest {
    @InjectMocks
    URLShortenerController urlShortenerController;

    @Mock
    RedisTemplate<String, UrlDto> redisTemplate;

    @Mock
    ValueOperations valueOperations;

    @Mock
    UrlDto urlDto;

    @Test
    void testCreate(){
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        urlShortenerController.create("http://www.google.com");
    }

    @Test
    void testCreateInvalidURL(){
        urlShortenerController.create("www.google.com");
    }

    @Test
    void testgetURL(){
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        urlShortenerController.getUrl("534555");
    }

    @Test
    void testgetURLValOp(){
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        when(valueOperations.get("534555")).thenReturn(urlDto);
        when(urlDto.getUrl()).thenReturn("http://localhost:9000/1");
        urlShortenerController.getUrl("534555");
    }

}
