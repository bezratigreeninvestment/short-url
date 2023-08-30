package com.ibezrati.shorturl.controller;

import com.ibezrati.shorturl.model.ShortUrlNotFoundException;
import com.ibezrati.shorturl.service.ShortUrlService;
import com.sun.istack.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class ShortUrlController {

    private final ShortUrlService shortUrlService;

    public ShortUrlController(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    @PostMapping("/shorten")
    @Validated
    public String shorten(@RequestBody @NotNull String originalUrl) {
        return shortUrlService.shorten(originalUrl);
    }

    @GetMapping("/expand")
    @Validated
    public String expand(@RequestParam("url") @NotNull String shortenedUrl) {
        try {
            return shortUrlService.expand(shortenedUrl);
        } catch (ShortUrlNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Short URL %s not found", shortenedUrl)
            );
        }
    }

}
