package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.domain.Userinfo;
import com.example.demo.dto.UserRequestDto;
import com.example.demo.repository.UserInfoRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;

    @Autowired
    public UserService(UserRepository userRepository, UserInfoRepository userInfoRepository) {
        this.userRepository = userRepository;
        this.userInfoRepository = userInfoRepository;
    }

    // 사용자 데이터를 분리하여 각각의 테이블에 저장
    public User saveUser(UserRequestDto userRequestDto) {
        // User 저장
        User user = new User();
        user.setName(userRequestDto.getName());
        userRepository.save(user);

        // UserInfo 저장
        Userinfo userInfo = new Userinfo();
        userInfo.setBirthYear(userRequestDto.getBirthYear());
        userInfo.setBirthMonth(userRequestDto.getBirthMonth());
        userInfo.setBirthDate(userRequestDto.getBirthDate());
        userInfo.setBirthTime(userRequestDto.getBirthTime());
        userInfo.setLunar(userRequestDto.isLunar());
        userInfo.setGender(userRequestDto.getGender());
        userInfo.setUser(user); // User와 연관 설정

        userInfoRepository.save(userInfo);
        return user;
    }
}
