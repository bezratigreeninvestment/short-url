package com.ibezrati.shorturl.configuration;

import com.ibezrati.shorturl.operation.ShortUrlCodeGenerator;
import com.ibezrati.shorturl.repository.ShortUrlRepository;
import com.ibezrati.shorturl.service.ShortUrlService;
import com.ibezrati.shorturl.service.ShortUrlServiceImpl;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public ShortUrlService shortUrlService(ShortUrlRepository shortUrlRepository, ShortUrlProperties shortUrlProperties, ShortUrlCodeGenerator shortUrlCodeGenerator) {
        return new ShortUrlServiceImpl(shortUrlRepository, shortUrlProperties, shortUrlCodeGenerator);
    }

    @Bean
    public ShortUrlCodeGenerator shortUrlCodeGenerator(ShortUrlProperties shortUrlProperties) {
        return new ShortUrlCodeGenerator(shortUrlProperties);
    }
}
