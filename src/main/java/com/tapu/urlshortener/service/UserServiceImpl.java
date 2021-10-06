package com.tapu.urlshortener.service;


import com.tapu.urlshortener.entities.User;
import com.tapu.urlshortener.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService{

    UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findOneById(id);
    }

    @Override
    public User findUserByName(String username) {
        return userRepository.findOneByUsername(username);
    }

}