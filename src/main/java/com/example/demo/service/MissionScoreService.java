package com.example.demo.service;

import com.example.demo.domain.Mission;
import com.example.demo.domain.Ranking;
import com.example.demo.domain.User;
import com.example.demo.repository.MissionRepository;
import com.example.demo.repository.RankingRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

    // Mission 데이터를 활용해 Ranking 저장 (로그인 사용자 정보 자동 반영)
    public Ranking saveScore(int missionCnt, int missionPnt) {
        // 현재 인증된 사용자 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // 사용자 이름 가져오기

        // UserDetails 사용 시
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String name = userDetails.getUsername(); // 사용자의 이름 또는 ID
            Long userId = getUserIdByName(name); // 사용자 ID를 데이터베이스에서 조회

            // Mission 테이블에 데이터를 저장
            Mission mission = new Mission(userId, missionCnt, missionPnt);
            missionRepository.save(mission);

            // totalPnt 계산 후 Ranking 테이블에 저장
            int totalPnt = missionCnt * missionPnt;
            Ranking ranking = new Ranking(userId, totalPnt, name);
            return rankingRepository.save(ranking);

        }

        throw new IllegalArgumentException("로그인된 사용자 정보가 없습니다.");
    }

    // 사용자 이름으로 ID를 조회하는 메서드
    private Long getUserIdByName(String name) {
        return userRepository.findByName(name)
                .map(User::getId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
    }

    // 상위 5개 랭킹 조회
    public List<Ranking> getTop5MissionScores() {
        return rankingRepository.findTop5ByOrderByTotalPntDesc();
    }
}
