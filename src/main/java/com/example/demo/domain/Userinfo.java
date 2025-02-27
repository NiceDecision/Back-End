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

    @Column(name = "birth_year")
    private int birthYear;

    @Column(name = "birth_month")
    private int birthMonth;

    @Column(name = "birth_date")
    private int birthDate;

    @Column(name = "birth_time")
    private String birthTime;

    @Column(name = "is_lunar")
    private boolean isLunar;

    @Column(length = 10)
    private String gender;

    @OneToOne
    @JoinColumn(name = "user_id") // user 테이블의 user_id 컬럼과 연결
    private User user;
}
