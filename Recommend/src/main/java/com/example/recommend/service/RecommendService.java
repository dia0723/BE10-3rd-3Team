package com.example.recommend.service;

import com.example.recommend.client.AccountClient;
import com.example.recommend.client.PlaceClient;
import com.example.recommend.dto.request.PlaceSearchRequest;
import com.example.recommend.dto.response.PlaceResponseDto;
import com.example.recommend.dto.request.RecommendKeywordRequestDto;
import com.example.recommend.dto.request.RecommendLocationRequestDto;
import com.example.recommend.dto.response.IdAccountResponseDto;
//import com.example.recommend.dto.response.RecommendResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class RecommendService {

    // 🔗 FeignClient를 통해 account-service와 place-service 연동
    private final AccountClient accountClient;
    private final PlaceClient placeClient;

    /**
     * 키워드 기반으로 추천 장소 목록을 반환하는 메서드
     * - 사용자의 유효성을 검증 (토큰을 통해 FeignClient로 검증)
     * - 키워드 기반 추천 장소 리스트를 반환
     */
    public List<PlaceResponseDto> recommendListKeyword(String Token, PlaceSearchRequest keywordDto) {
        // 1. 로그인 사용자 유효성 검증 (단순 인증용)
//        accountClient.getAccountId("Bearer " + pureToken);
        accountClient.getAccountId(Token);
        // 2. 키워드 기반 장소 리스트 반환

        return placeClient.searchPlaces(keywordDto);
    }

    public List<PlaceResponseDto> recommendListLocation(String Token, PlaceSearchRequest LocationDto) {
        // 1. 로그인 사용자 유효성 검증 (단순 인증용)
//        accountClient.getAccountId("Bearer " + pureToken);
        accountClient.getAccountId(Token);
        // 2. 지역 기반 장소 리스트 반환
        return placeClient.searchPlaces(LocationDto);
    }

    /**
     * 키워드 기반 장소 중 하나를 랜덤으로 골라 추천하는 메서드
     * - 유저 정보 확인 후
     * - 키워드 기반 추천 장소를 조회
     * - 랜덤으로 하나 선택해서 추천
     */
    public PlaceResponseDto recommendRandom(String Token, PlaceSearchRequest keywordDto) {
//        // 1. 로그인 유저 정보 조회 (닉네임 포함)
//        IdAccountResponseDto account = accountClient.getAccountId("Bearer " + pureToken);
//        // 2. 키워드에 해당하는 장소 리스트 조회
        accountClient.getAccountId(Token);
        List<PlaceResponseDto> places = placeClient.searchPlaces(keywordDto);
        // 3. 결과가 비어있을 경우 예외 처리
        if (places == null || places.isEmpty()) {
            // 아무것도 없으면 기본 메시지 반환
            return new PlaceResponseDto(null, "추천 장소가 없습니다.", "", "", "","");
        }
        return places.get(new Random().nextInt(places.size()));
    }
}
