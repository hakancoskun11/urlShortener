package com.tapu.urlshortener.service;

import com.tapu.urlshortener.entities.Url;

import java.util.List;

public interface UrlService {
    public Url saveUrl(Url url);
    public Url findUrlByLongUrl(String longUrl);
    public Url findUrlByShortUrl(String shortUrl);
    public List<Url> listUrlByIdIn(List<Long> urlIdList);
}
