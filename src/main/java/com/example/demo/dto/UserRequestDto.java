package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
    private String name;
    private int birthYear;
    private int birthMonth;
    private int birthDate;
    private String birthTime;
    private boolean isLunar;
    private String gender;
}
