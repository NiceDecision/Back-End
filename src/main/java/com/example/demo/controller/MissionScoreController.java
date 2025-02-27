package com.example.demo.controller;

import com.example.demo.domain.Ranking;
import com.example.demo.service.MissionScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mission")
public class MissionScoreController {

    private final MissionScoreService missionScoreService;

    @Autowired
    public MissionScoreController(MissionScoreService missionScoreService) {
        this.missionScoreService = missionScoreService;
    }

    // 점수 저장 API (userId와 name을 받지 않고, 자동으로 인증된 사용자 정보 사용)
    @PostMapping("/score")
    public ResponseEntity<Ranking> saveScore(@RequestParam int missionCnt,
                                             @RequestParam int missionPnt) {
        Ranking ranking = missionScoreService.saveScore(missionCnt, missionPnt);
        return new ResponseEntity<>(ranking, HttpStatus.CREATED);
    }

    // 랭킹 조회 API
    @GetMapping("/rank")
    public ResponseEntity<List<Ranking>> getTop5MissionScores() {
        List<Ranking> ranking = missionScoreService.getTop5MissionScores();
        return ResponseEntity.ok(ranking);
    }
}
