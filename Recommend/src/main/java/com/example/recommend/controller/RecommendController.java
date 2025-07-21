package com.example.recommend.controller;

import com.example.recommend.dto.request.PlaceSearchRequest;
import com.example.recommend.dto.request.RecommendKeywordRequestDto;
import com.example.recommend.dto.request.RecommendLocationRequestDto;
import com.example.recommend.dto.response.PlaceResponseDto;
//import com.example.recommend.dto.response.RecommendResultResponse;
import com.example.recommend.service.RecommendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/recommend")
public class RecommendController {
    //recommend의 경우
    //여러 서비스(account, place 등)의 데이터를 조회해서 추천 결과를 판단하고 반환하는 것만 처리

    private final RecommendService recommendService;
    public RecommendController(RecommendService recommendService) {
        this.recommendService = recommendService;
    }
    @GetMapping("/test")
    public String test(){
        log.info("test is processing");
        return "test is processing";
    }
    /*
    2025/07/14
    recommend는 각 서비스의 데이터를 조회해서 추천결과를 판단하고 반환하는것만 처리한다.
    이를 고려하였을때
    1. 키워드에 맞는 place의 결과값을 가져오는 list 처리
    2. 랜덤값을 통해 place중 하나를 랜덤으로 가져오는 random 처리
    으로 현재 고려해볼수있다.
    그렇다면 첫번째를 구현한다고 한다면
    request로 들어오는 키워드를 Dto형식으로 받아서 이를 fegin처리로 구현할것인가?
     */
    @PostMapping("/list/keyword")
    public ResponseEntity<List<PlaceResponseDto>> recommendListKeyword(
            @RequestHeader("Authorization") String token,
            @RequestBody PlaceSearchRequest requestDto
    ) {
//        String pureToken = extractToken(token);  // "Bearer " 제거
        List<PlaceResponseDto> result = recommendService.recommendListKeyword(token, requestDto);
        return ResponseEntity.ok(result);
    }
    @PostMapping("/list/location")
    public ResponseEntity<List<PlaceResponseDto>> recommendListLocation(
            @RequestHeader("Authorization") String token,
            @RequestBody PlaceSearchRequest requestDto
    ) {
//        String pureToken = extractToken(token);  // "Bearer " 제거
        List<PlaceResponseDto> result = recommendService.recommendListLocation(token, requestDto);
        return ResponseEntity.ok(result);
    }
    // 📌 키워드 기반 랜덤 장소 추천
    @PostMapping("/random")
    public ResponseEntity<PlaceResponseDto> recommendRandom(
            @RequestHeader("Authorization") String token,
            @RequestBody PlaceSearchRequest keywordDto
    ) {
//        String pureToken = extractToken(token);
        return ResponseEntity.ok(recommendService.recommendRandom(token, keywordDto));
    }

    private String extractToken(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return token;
    }
}
