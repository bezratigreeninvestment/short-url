package com.ibezrati.shorturl.service;

import com.ibezrati.shorturl.configuration.ShortUrlProperties;
import com.ibezrati.shorturl.entity.ShortUrl;
import com.ibezrati.shorturl.model.ShortUrlNotFoundException;
import com.ibezrati.shorturl.operation.ShortUrlCodeGenerator;
import com.ibezrati.shorturl.repository.ShortUrlRepository;

import javax.transaction.Transactional;
import java.util.Optional;

public class ShortUrlServiceImpl implements ShortUrlService {
    private final ShortUrlRepository shortUrlRepository;
    private final ShortUrlProperties shortUrlProperties;
    private final ShortUrlCodeGenerator shortUrlCodeGenerator;

    public ShortUrlServiceImpl(ShortUrlRepository shortUrlRepository, ShortUrlProperties shortUrlProperties, ShortUrlCodeGenerator shortUrlCodeGenerator) {
        this.shortUrlRepository = shortUrlRepository;
        this.shortUrlProperties = shortUrlProperties;
        this.shortUrlCodeGenerator = shortUrlCodeGenerator;
    }

    @Transactional
    @Override
    public String shorten(String originalUrl) {
        String code = shortUrlCodeGenerator.generate(originalUrl);
        String shortUrl = shortUrlProperties.getDomain() + code;

        // TODO: some sort of a synchronization lock that is cross-instance and specific to short url, i.e.: redis key
        // TODO: avoid synchronization with synchronized or any other instance specific approach like ConcurrentHashMap
        ShortUrl entity = shortUrlRepository.findByShortened(shortUrl);

        if (entity != null) {
            return shortUrl;
        }

        entity = new ShortUrl();

        entity.setExpanded(originalUrl);
        entity.setShortened(shortUrl);

        shortUrlRepository.save(entity);

        return shortUrl;
    }

    @Override
    public String expand(String shortUrl) throws ShortUrlNotFoundException {
        return Optional.ofNullable(shortUrlRepository.findByShortened(shortUrl)).orElseThrow(ShortUrlNotFoundException::new).getExpanded();
    }

}
