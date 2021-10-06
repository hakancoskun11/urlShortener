package com.tapu.urlshortener.repository;

import java.util.List;

import com.tapu.urlshortener.entities.UserUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserUrlRepository extends JpaRepository<UserUrl, Long> {

    public List<UserUrl> findByUserId(Long userId);
    public List<UserUrl> findByUserIdAndUrlId(Long userId, Long urlId);

}