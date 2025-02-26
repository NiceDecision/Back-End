package com.example.demo.repository;

import com.example.demo.domain.Userinfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<Userinfo, Long> {
}
