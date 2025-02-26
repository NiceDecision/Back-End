package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Userinfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private int birthYear;
    private int birthMonth;
    private int birthDate;
    private int birthTime;
    private boolean isLunar;
    private String gender;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;
}
