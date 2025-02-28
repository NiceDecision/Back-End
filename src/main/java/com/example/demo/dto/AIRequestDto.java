package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AIRequestDto {

    private String question;

    @JsonProperty("gpt_mbti")
    private GptMbtiDto gptMbti;

    @JsonProperty("user_info")
    private UserInfoDto userInfo;
}
