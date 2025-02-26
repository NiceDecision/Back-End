package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserRequestDto {
    private Long userId;
    private int birthYear;
    private int birthMonth;
    private int birthDate;
    private int birthTime;
    boolean isLunar;
}
