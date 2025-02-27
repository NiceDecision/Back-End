package com.example.demo.controller;

import com.example.demo.service.AIService;
import com.example.demo.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ai")
public class AIController {
    private final AIService aiService;
    @GetMapping("/chat-history/{userId}")
    public ResponseEntity<List<String>> getChat(@PathVariable Long userId) {
        return ResponseEntity.ok(aiService.getChat(userId));
    }
}
