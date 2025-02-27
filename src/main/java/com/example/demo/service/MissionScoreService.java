package com.example.demo.service;

import com.example.demo.domain.Mission;
import com.example.demo.domain.Ranking;
import com.example.demo.repository.MissionRepository;
import com.example.demo.repository.RankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MissionScoreService {

    private final MissionRepository missionRepository;
    private final RankingRepository rankingRepository;

    @Autowired
    public MissionScoreService(MissionRepository missionRepository, RankingRepository rankingRepository) {
        this.missionRepository = missionRepository;
        this.rankingRepository = rankingRepository;
    }

    // Mission 데이터를 활용해 Ranking 저장
    public Ranking saveScore(Long userId, String name, int missionCnt, int missionPnt) {
        // Mission 테이블에 데이터를 저장
        Mission mission = new Mission(userId, missionCnt, missionPnt);
        missionRepository.save(mission);

        // totalPnt 계산 후 Ranking 테이블에 저장
        int totalPnt = missionCnt * missionPnt;
        Ranking ranking = new Ranking(userId, totalPnt, name);
        return rankingRepository.save(ranking);
    }

    // 상위 5개 랭킹 조회
    public List<Ranking> getTop5GameScores() {
        return rankingRepository.findTop5ByOrderByTotalPntDesc();
    }
}
