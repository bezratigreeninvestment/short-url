package com.ibezrati.shorturl.service;

import com.ibezrati.shorturl.model.ShortUrlNotFoundException;

public interface ShortUrlService {

    String shorten(String originalUrl);

    /**
     * @param shortUrl: the shortened URL to expand
     * @return the long version of the shortened URL
     * @throws ShortUrlNotFoundException when then short URL is not found
     */
    String expand(String shortUrl) throws ShortUrlNotFoundException;
}
