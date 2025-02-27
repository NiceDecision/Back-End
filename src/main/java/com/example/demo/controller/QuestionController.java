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

    @PostMapping
    public ResponseEntity<String> askQuestion(@RequestBody QuestionRequestDto questionRequestDto) {
        if (questionRequestDto.getUserId() == null) {
            return ResponseEntity.status(401).body("User ID not found in cookie.");
        }
        String response = aiService.sendQuestionToAI(questionRequestDto.getUserId(), questionRequestDto.getQuestion(), questionRequestDto.getGptMbti());
        return ResponseEntity.ok(response);
    }

}
