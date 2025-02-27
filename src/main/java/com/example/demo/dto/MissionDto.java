package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MissionDto {
    @JsonProperty("userId")
    private Long userId;

    @JsonProperty("missionCnt")
    private int missionCnt;

    @JsonProperty("missionPnt")
    private int missionPnt;
}
