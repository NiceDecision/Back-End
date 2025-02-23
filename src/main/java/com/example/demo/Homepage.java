package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Homepage {

    //첫 페이지
    @GetMapping("/")
    public String homepage() {
        return "home";
    }
}