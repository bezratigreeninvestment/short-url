package com.ibezrati.shorturl.operation;

import com.google.common.hash.Hashing;
import com.ibezrati.shorturl.configuration.ShortUrlProperties;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;

public class ShortUrlCodeGenerator {

    // if you change this value, the generated url codes will change and existing short urls won't be found
    private static final int SEED = 10;

    private final ShortUrlProperties shortUrlProperties;

    public ShortUrlCodeGenerator(ShortUrlProperties shortUrlProperties) {
        this.shortUrlProperties = shortUrlProperties;
    }

    public String generate(String originalUrl) {
        return StringUtils.left(Hashing.murmur3_128(SEED).hashString(originalUrl, StandardCharsets.UTF_8).toString(), shortUrlProperties.getMaxLength());
    }
}
