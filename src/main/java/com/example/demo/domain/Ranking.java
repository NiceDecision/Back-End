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
    private String name;

    // Getter, Setter, 생성자 추가
    public Ranking() {}

    public Ranking(Long userId, int totalPnt, String name) {
        this.userId = userId;
        this.totalPnt = totalPnt;
        this.name = name;
    }
}
