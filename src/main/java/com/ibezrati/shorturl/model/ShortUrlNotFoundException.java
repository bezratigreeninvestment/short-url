package com.ibezrati.shorturl.model;

public class ShortUrlNotFoundException extends Exception {
    public ShortUrlNotFoundException() {
    }

    public ShortUrlNotFoundException(String message) {
        super(message);
    }
}
