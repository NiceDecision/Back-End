package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ranking")
public class Ranking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private int totalPnt;

    // name을 자동으로 User의 name과 연동
    @Transient // DB에 저장되지 않고 조회 시 사용
    private String name;

    public Ranking() {}

    public Ranking(Long userId, int totalPnt, String name) {
        this.userId = userId;
        this.totalPnt = totalPnt;
        this.name = name;
    }
}
