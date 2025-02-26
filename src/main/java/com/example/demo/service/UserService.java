package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.dto.UserRequestDto;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(UserRequestDto userRequestDto){
        User user = new User();
        BeanUtils.copyProperties(userRequestDto, user);
        return userRepository.save(user);
    }
}
