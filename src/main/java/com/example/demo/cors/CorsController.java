package com.example.demo.cors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CorsController {

    @CrossOrigin(origins = "http://localhost:3000", originPatterns = "http://34.64.192.124")
    @GetMapping("/api/data")
    public String getData() {
        return "CORS 테스트 성공!";
    }
}
