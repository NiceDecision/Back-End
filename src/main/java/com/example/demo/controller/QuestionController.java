package com.example.demo.controller;

import com.example.demo.dto.QuestionRequestDto;
import com.example.demo.service.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat/question")
public class QuestionController {

    private final AIService aiService;

    @Autowired
    public QuestionController(AIService aiService) {
        this.aiService = aiService;
    }

    // 질문과 MBTI를 동시에 받는 엔드포인트
    @PostMapping
    public ResponseEntity<String> askQuestion(@RequestBody QuestionRequestDto questionRequestDto,
                                              @CookieValue(value = "userId", required = false) Long userId) {
        if (userId == null) {
            return ResponseEntity.status(401).body("User ID not found in cookie.");
        }

        // AI 서버로 질문과 사용자 정보를 함께 전송
        String response = aiService.sendQuestionToAI(userId, questionRequestDto);
        return ResponseEntity.ok(response);
    }
}
