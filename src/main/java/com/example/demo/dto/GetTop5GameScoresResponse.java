package com.example.demo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetTop5GameScoresResponse {

    private int rank;
    private String name;
    private int totalPnt;

    @Builder
    private GetTop5GameScoresResponse(int rank, String name, int totalPnt) {
        this.rank = rank;
        this.name = name;
        this.totalPnt = totalPnt;
    }
    public static GetTop5GameScoresResponse of(int rank, String name, int totalPnt) {
        return new GetTop5GameScoresResponse(rank, name, totalPnt);
    }
}
