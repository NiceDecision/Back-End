package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserInfoDto {

    private String name;
    private String birth;
    private String birthTime;
    private boolean isLunar;
    private String gender;
}
