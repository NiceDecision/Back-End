package com.example.demo.repository;

import com.example.demo.domain.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, Long> {
    // totalPnt 기준으로 내림차순 정렬 후 상위 5개 데이터 반환
    List<Ranking> findTop5ByOrderByTotalPntDesc();
}
