package com.example.demo.service;

import com.example.demo.domain.Mission;
import com.example.demo.domain.Ranking;
import com.example.demo.domain.User;
import com.example.demo.dto.MissionDto;
import com.example.demo.repository.MissionRepository;
import com.example.demo.repository.RankingRepository;
import com.example.demo.repository.UserRepository;
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

        // User의 name을 가져오기
        Optional<User> optionalUser = userRepository.findById(userId);
        String userName = optionalUser.map(User::getName).orElse("Unknown");

        // totalPnt 계산 후 Ranking 테이블에 저장
        int totalPnt = missionCnt * missionPnt;
        Ranking ranking = new Ranking();
        ranking.setUserId(userId);
        ranking.setTotalPnt(totalPnt);
        ranking.setName(userName); // User의 name 설정

        return rankingRepository.save(ranking);
    }

    // 상위 5개 랭킹 조회
    public List<Ranking> getTop5GameScores() {
        return rankingRepository.findTop5ByOrderByTotalPntDesc();
    }
}