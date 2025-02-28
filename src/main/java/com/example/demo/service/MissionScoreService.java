package com.example.demo.service;

import com.example.demo.domain.Mission;
import com.example.demo.domain.Ranking;
import com.example.demo.domain.User;
import com.example.demo.dto.GetTop5GameScoresResponse;
import com.example.demo.dto.MissionDto;
import com.example.demo.repository.MissionRepository;
import com.example.demo.repository.RankingRepository;
import com.example.demo.repository.UserRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MissionScoreService {

    private final MissionRepository missionRepository;
    private final RankingRepository rankingRepository;
    private final UserRepository userRepository;

    @Autowired
    public MissionScoreService(MissionRepository missionRepository, RankingRepository rankingRepository, UserRepository userRepository) {
        this.missionRepository = missionRepository;
        this.rankingRepository = rankingRepository;
        this.userRepository = userRepository;
    }

    // Mission 데이터를 활용해 Ranking 저장
    public Ranking saveScore(MissionDto missionDto) {
        Long userId = missionDto.getUserId();
        int missionCnt = missionDto.getMissionCnt();
        int missionPnt = missionDto.getMissionPnt();

        // Mission 테이블에 데이터를 저장
        Mission mission = new Mission(userId, missionCnt, missionPnt);
        missionRepository.save(mission);

        // totalPnt 계산 후 Ranking 테이블에 저장
        int totalPnt = missionCnt * missionPnt;
        Ranking ranking = rankingRepository.findByUserId(userId);
        ranking.setTotalPnt(totalPnt);
        return rankingRepository.save(ranking);
    }

    // 상위 5개 랭킹 조회
    public List<GetTop5GameScoresResponse> getTop5GameScores() {
        List<Ranking> rankings =  rankingRepository.findTop5ByOrderByTotalPntDesc();
        List<GetTop5GameScoresResponse> responses = new ArrayList<>();
        for(int i = 0; i < rankings.size(); i++) {
            User user = userRepository.findById(rankings.get(i).getUserId()).get();
            responses.add(GetTop5GameScoresResponse.of(i+1,user.getName(),rankings.get(i).getTotalPnt()));
        }
        return responses;
    }
}