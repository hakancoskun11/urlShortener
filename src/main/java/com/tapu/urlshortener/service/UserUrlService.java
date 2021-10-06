package com.tapu.urlshortener.service;

import com.tapu.urlshortener.entities.UserUrl;

import java.util.List;

public interface UserUrlService {
    public UserUrl saveUserUrl(UserUrl userUrl);
    public List<UserUrl> findUserUrlByUserId(Long userId);
    public List<UserUrl> findUserUrlByUserIdAndUrlId(Long userId, Long urlId);
}
