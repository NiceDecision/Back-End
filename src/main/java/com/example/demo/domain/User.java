package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // 이 클래스가 JPA에서 사용될 엔티티임을 지정
@Table(name = "user") // 데이터베이스의 user 테이블과 매핑
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id") // user 테이블의 user_id 컬럼과 매핑
    private Long userId;

    @Column(nullable = false, length = 255) // name 컬럼 설정
    private String name;

    // Getter 메서드 추가
    public Long getId() {
        return userId;
    }

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Userinfo userInfo;
}
