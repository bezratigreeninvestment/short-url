package com.ibezrati.shorturl.repository;

import com.ibezrati.shorturl.entity.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {

    ShortUrl findByShortened(String shortened);

}
