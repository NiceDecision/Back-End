package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionRequestDto {

    private String question;

    @JsonProperty("gpt_mbti")
    private String gpt_mbti;
}
