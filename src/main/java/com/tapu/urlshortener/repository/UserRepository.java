package com.tapu.urlshortener.repository;


import com.tapu.urlshortener.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User findOneByUsername(String username);

    User findOneById(Long id);
}