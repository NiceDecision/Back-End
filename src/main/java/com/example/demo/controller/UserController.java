package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.dto.UserRequestDto;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserRequestDto userRequestDto) {
        User savedUser = userService.saveUser(userRequestDto);

        // 사용자 ID를 쿠키에 저장
        ResponseCookie cookie = ResponseCookie.from("userId", savedUser.getId().toString())
                .httpOnly(true)  // JavaScript 접근 차단 (보안 강화)
                .path("/")        // 모든 경로에서 쿠키 사용 가능
                .maxAge(7 * 24 * 60 * 60) // 쿠키 유효 기간 (7일)
                .sameSite("Lax")  // SameSite 설정
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(savedUser);
    }
}
