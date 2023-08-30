package com.ibezrati.shorturl.controller;

import com.google.common.collect.ImmutableMap;
import com.ibezrati.shorturl.configuration.ShortUrlProperties;
import com.ibezrati.shorturl.repository.ShortUrlRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ShortUrlControllerTest {

    @Value("${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ShortUrlProperties shortUrlProperties;

    @Autowired
    private ShortUrlRepository shortUrlRepository;

    @BeforeEach
    @Transactional
    void setup() {
        shortUrlRepository.deleteAll();
    }

    @Test
    void shorten() {
        String shortUrl = restTemplate.postForObject("http://localhost:" + port + "/shorten", "google.com", String.class);

        assertNotNull(shortUrl);
        assertTrue(shortUrl.startsWith(shortUrlProperties.getDomain()), String.format("%s doesn't start with domain %s", shortUrl, shortUrlProperties.getDomain()));
        assertEquals(shortUrl, shortUrl.trim());
        assertEquals(shortUrlProperties.getDomain().length() + shortUrlProperties.getMaxLength(), shortUrl.length());
    }

    @Test
    void expand() {
        // Given
        final String originalUrl = "https://www.notarius.com/en/";

        // Execute
        final String shortUrl = restTemplate.postForObject("http://localhost:" + port + "/shorten", originalUrl, String.class);

        // Verify
        assertNotNull(shortUrl);
        assertTrue(shortUrl.startsWith(shortUrlProperties.getDomain()), String.format("%s doesn't start with domain %s", shortUrl, shortUrlProperties.getDomain()));

        // Execute
        final String expandedUrl = restTemplate.getForObject("http://localhost:" + port + "/expand?url={url}", String.class, ImmutableMap.of("url", shortUrl));

        // Verify
        assertNotNull(expandedUrl);
        assertEquals(originalUrl, expandedUrl);

    }
}