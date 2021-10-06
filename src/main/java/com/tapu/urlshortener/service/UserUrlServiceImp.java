package com.tapu.urlshortener.service;

import com.tapu.urlshortener.entities.UserUrl;
import com.tapu.urlshortener.repository.UserUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userUrlService")
public class UserUrlServiceImp implements UserUrlService{

    @Autowired
    UserUrlRepository userUrlRepository;

    @Override
    public UserUrl saveUserUrl(UserUrl userUrl) {
        return userUrlRepository.saveAndFlush(userUrl);
    }

    @Override
    public List<UserUrl> findUserUrlByUserId(Long userId) {
        return userUrlRepository.findByUserId(userId);
    }

    @Override
    public List<UserUrl>  findUserUrlByUserIdAndUrlId(Long userId, Long urlId) {
        return userUrlRepository.findByUserIdAndUrlId(userId, urlId);
    }

}