package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.domain.Userinfo;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// UserService.java
import com.example.demo.dto.UserRequestDto;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;

    @Autowired
    public UserService(UserRepository userRepository, UserInfoRepository userInfoRepository) {
        this.userRepository = userRepository;
        this.userInfoRepository = userInfoRepository;
    }

    public User saveUser(UserRequestDto userRequestDto) {
        // User 엔티티 생성
        User user = new User();
        user.setName(userRequestDto.getName());
        user = userRepository.save(user); // user 테이블에 저장

        // UserInfo 엔티티 생성
        Userinfo userInfo = new Userinfo();
        userInfo.setBirthYear(userRequestDto.getBirthYear());
        userInfo.setBirthMonth(userRequestDto.getBirthMonth());
        userInfo.setBirthDate(userRequestDto.getBirthDate());
        userInfo.setBirthTime(userRequestDto.getBirthTime());
        userInfo.setLunar(userRequestDto.isLunar()); // 메서드 이름 수정
        userInfo.setGender(userRequestDto.getGender());
        userInfo.setUser(user); // User와 연결 설정

        userInfoRepository.save(userInfo); // user_info 테이블에 저장

        return user;
    }
}
