package com.appletest.urlshortener.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.appletest.urlshortener.model.UrlDto;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author JagadeeshPrakashP
 * Main controller to run the requestmapping url
 */
@Slf4j
@RestController
@RequestMapping(value = "/rest/url")
public class URLShortenerController {

    @Autowired
    private RedisTemplate<String, UrlDto> redisTemplate;

    @Value("${redis.ttl}")
    private long ttl;

    /**
     * Create the request URL entry to RedisConfig using RedisTemplate
     * and also returning the url id as part of response header
     * @param url
     * @return
     */
	@PostMapping
    public ResponseEntity create(@RequestBody final String url) {
        final UrlValidator urlValidator = new UrlValidator(new String[]{"http", "https"});
        if (!urlValidator.isValid(url)) {
            return ResponseEntity.badRequest().body(new Error("Invalid URL."));
        }

        final UrlDto urlDto = UrlDto.create(url);
        log.info("URL id generated = {}", urlDto.getId());
        redisTemplate.opsForValue().set(urlDto.getId(), urlDto, ttl, TimeUnit.SECONDS);
        return ResponseEntity.noContent().header("id", urlDto.getId()).build();
    }

    /**
     * Retrive the shorten URL details from ID from Redis template
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity getUrl(@PathVariable final String id) {
        final UrlDto urlDto = redisTemplate.opsForValue().get(id);
        if (Objects.isNull(urlDto)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error("No such key exists."));
        } else {
            log.info("URL retrieved = {}", urlDto.getUrl());
        }

        return ResponseEntity.ok(urlDto);
    }
}
