package com.tapu.urlshortener.service;

import com.tapu.urlshortener.entities.User;

public interface UserService {
    public User saveUser(User user);
    public User findUserById(Long id);
    public User findUserByName(String username);
}
