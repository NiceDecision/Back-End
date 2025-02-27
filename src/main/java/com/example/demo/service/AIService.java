package com.example.demo.service;

import com.example.demo.domain.History;
import com.example.demo.domain.User;
import com.example.demo.domain.Userinfo;
import com.example.demo.dto.AIRequestDto;
import com.example.demo.dto.GptMbtiDto;
import com.example.demo.dto.UserInfoDto;
import com.example.demo.repository.HistoryRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserInfoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class AIService {

    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;
    private final RestTemplate restTemplate;
    private final HistoryRepository historyRepository;

    @Autowired
    public AIService(UserRepository userRepository, UserInfoRepository userInfoRepository,
        HistoryRepository historyRepository) {
        this.userRepository = userRepository;
        this.userInfoRepository = userInfoRepository;
        this.historyRepository = historyRepository;
        this.restTemplate = new RestTemplate();
    }

    @Transactional
    public String sendQuestionToAI(Long userId, String question, String gptMbti) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Userinfo> optionalUserInfo = userInfoRepository.findByUserId(userId);

        if (optionalUser.isEmpty() || optionalUserInfo.isEmpty()) {
            throw new RuntimeException("User information not found!");
        }

        User user = optionalUser.get();
        Userinfo userinfo = optionalUserInfo.get();

        String birthDate = String.format("%04d-%02d-%02d",
            userinfo.getBirthYear(),
            userinfo.getBirthMonth(),
            userinfo.getBirthDate());

        UserInfoDto userInfo = new UserInfoDto(
            user.getName(),
            birthDate,
            userinfo.getBirthTime(),
            userinfo.isLunar(),
            userinfo.getGender()
        );
        AIRequestDto aiRequest = new AIRequestDto(
            question,
            new GptMbtiDto(gptMbti),
            userInfo
        );
        String aiUrl = "http://34.64.192.124:8000/ai/fortune";

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<AIRequestDto> request = new HttpEntity<>(aiRequest, headers);

            System.out.println(
                "Request to AI Server: " + new ObjectMapper().writeValueAsString(aiRequest));

            ResponseEntity<String> response = restTemplate.postForEntity(aiUrl, request,
                String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                String responseBody = response.getBody();
                System.out.println(responseBody);
                String key = "\"response\":\"";
                int startIndex = responseBody.indexOf(key) + key.length();
                String responseValue = responseBody.substring(startIndex);
                historyRepository.save(new History(question, userId));
                historyRepository.save(new History(responseValue, userId));
                return responseValue;
            } else {
                throw new RuntimeException("Failed to get response from AI server.");
            }

        } catch (Exception e) {
            throw new RuntimeException(
                "Error occurred while connecting to AI server: " + e.getMessage());
        }
    }

    public List<String> getChat(Long userId) {
        return historyRepository.findByUserId(userId).stream().map(History::getHistory).toList();
    }
}
