package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MissionDto {
    private Long userId;
    private String name;
    private int missionCnt;
    private int missionPnt;
}
