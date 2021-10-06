package com.tapu.urlshortener.repository;

import java.util.List;

import com.tapu.urlshortener.entities.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UrlRepository extends JpaRepository<Url, Long>  {

    public Url findOneByShortUrl(String shortUrl);
    public Url findOneByLongUrl(String longUrl);
    public List<Url> findByIdIn(List<Long> urlIdList);

}
