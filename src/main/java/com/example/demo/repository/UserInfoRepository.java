package com.example.demo.repository;

import com.example.demo.domain.Userinfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<Userinfo, Long> {

    // User ID를 기반으로 Userinfo를 조회
    Optional<Userinfo> findByUserId(Long userId);
}
