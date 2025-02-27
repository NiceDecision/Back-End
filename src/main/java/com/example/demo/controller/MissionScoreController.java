package com.example.demo.controller;

import com.example.demo.domain.Ranking;
import com.example.demo.dto.MissionDto;
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

    // 점수 저장 API
    @PostMapping
    public ResponseEntity<Ranking> saveScore(@RequestBody MissionDto missionDto){
        Ranking ranking = missionScoreService.saveScore(missionDto);
        return new ResponseEntity<>(ranking, HttpStatus.CREATED);
    }

    // 랭킹 조회 API
    @GetMapping("/rank")
    public ResponseEntity<List<Ranking>> getTop5GameScores() {
        List<Ranking> ranking = missionScoreService.getTop5GameScores();
        return ResponseEntity.ok(ranking);
    }
}