package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    private Long userId;
    @Getter
    private int missionCnt;
    @Getter
    private int missionPnt;

    // Getter, Setter, 생성자 추가
    public Mission() {}

    public Mission(Long userId, int missionCnt, int missionPnt) {
        this.userId = userId;
        this.missionCnt = missionCnt;
        this.missionPnt = missionPnt;
    }

}
