package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_info") // user_info 테이블과 매핑
@Getter
@Setter
@NoArgsConstructor
public class Userinfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // UserInfo의 고유 ID

    @Column(name = "birthYear")
    private int birthYear;

    @Column(name = "birthMonth")
    private int birthMonth;

    @Column(name = "birthDate")
    private int birthDate;

    @Column(name = "birthTime")
    private int birthTime;

    @Column(name = "isLunar")
    private boolean isLunar;

    @Column(length = 10)
    private String gender;

    @OneToOne
    @JoinColumn(name = "user_id") // user 테이블의 user_id 컬럼과 연결
    private User user;
}
