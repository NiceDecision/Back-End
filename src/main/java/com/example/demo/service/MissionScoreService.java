package com.example.demo.service;

import com.example.demo.domain.Mission;
import com.example.demo.domain.Ranking;
import com.example.demo.dto.MissionDto;
import com.example.demo.dto.UserRequestDto;
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
    public Ranking saveScore(MissionDto missionDto) {
        // MissionDto에서 데이터를 추출
        Long userId = missionDto.getUserId();
        String name = missionDto.getName();
        int missionCnt = missionDto.getMissionCnt();
        int missionPnt = missionDto.getMissionPnt();

        // Mission 테이블에 데이터를 저장
        Mission mission = new Mission(userId, name, missionCnt, missionPnt);
        missionRepository.save(mission);

        // totalPnt 계산 후 Ranking 테이블에 저장
        int totalPnt = missionCnt * missionPnt;
        Ranking ranking = new Ranking();
        ranking.setUserId(userId);
        ranking.setName(name);
        ranking.setTotalPnt(totalPnt);

        return rankingRepository.save(ranking);
    }

    // 상위 5개 랭킹 조회
    public List<Ranking> getTop5GameScores() {
        return rankingRepository.findTop5ByOrderByTotalPntDesc();
    }
}
