package com.tapu.urlshortener.service;

import com.tapu.urlshortener.entities.Url;
import com.tapu.urlshortener.repository.UrlRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("urlService")
public class UrlServiceImpl implements UrlService{


    UrlRepository urlRepository;

    @Override
    public Url saveUrl(Url url) {
        return urlRepository.saveAndFlush(url);
    }

    @Override
    public Url findUrlByLongUrl(String longUrl) {
        return urlRepository.findOneByLongUrl(longUrl);
    }

    @Override
    public Url findUrlByShortUrl(String shortUrl) {
        return urlRepository.findOneByShortUrl(shortUrl);
    }

    @Override
    public List<Url> listUrlByIdIn(List<Long> urlIdList) {
        return urlRepository.findByIdIn(urlIdList);
    }

}