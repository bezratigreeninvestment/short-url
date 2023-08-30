package com.ibezrati.shorturl.operation;

import com.ibezrati.shorturl.configuration.ShortUrlProperties;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ShortUrlCodeGeneratorTest {

    @Test
    void generate() {
        // Given
        ShortUrlProperties properties = new ShortUrlProperties();
        ShortUrlCodeGenerator generator = new ShortUrlCodeGenerator(properties);

        properties.setMaxLength(10);

        // Execute & Verify
        assertEquals("b0579bed91", generator.generate("google.com"));
    }
}