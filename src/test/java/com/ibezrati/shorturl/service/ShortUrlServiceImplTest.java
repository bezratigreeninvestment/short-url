package com.ibezrati.shorturl.service;

import com.ibezrati.shorturl.model.ShortUrlNotFoundException;
import com.ibezrati.shorturl.repository.ShortUrlRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ShortUrlServiceImplTest {

    @Autowired
    private ShortUrlService instance;

    @Autowired
    private ShortUrlRepository shortUrlRepository;

    @BeforeEach
    @Transactional
    void setup() {
        shortUrlRepository.deleteAll();
    }

    @Test
    void shorten() {
        assertEquals("http://ibezrati.io/b0579bed91", instance.shorten("google.com"));
    }

    @Test
    void shortenExistingUrl() {
        assertEquals("http://ibezrati.io/b0579bed91", instance.shorten("google.com"));
        assertEquals("http://ibezrati.io/b0579bed91", instance.shorten("google.com"));
    }

    @Test
    void expand() throws ShortUrlNotFoundException {
        final String shortUrl = instance.shorten("google.com");
        assertEquals("http://ibezrati.io/b0579bed91", shortUrl);
        assertEquals("google.com", instance.expand(shortUrl));
    }

    @Test
    void expandNonexistentUrl() {
        assertThrows(ShortUrlNotFoundException.class, () -> instance.expand("domain.com"));
    }
}