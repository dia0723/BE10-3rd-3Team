package com.example.recommend.client;

//import com.example.book.controller.response.IdAccountResponse;
import com.example.recommend.dto.request.PlaceSearchRequest;
import com.example.recommend.dto.response.PlaceResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "PLACE") //어떤 마이크로서비스를 호출할지 지정
public interface PlaceClient {

    // ✅ keyword, location, title 조합 모두 처리할 단일 search
    @PostMapping("/place/search")
    List<PlaceResponseDto> searchPlaces(@RequestBody PlaceSearchRequest request);
    /*
    place측 처리
    @PostMapping("/search")
    public List<Place> searchPlaces(@RequestBody PlaceSearchRequest request) {
     */

    // (기존 필요 없는 것들은 일단 주석처리 또는 삭제)
    /*
    @GetMapping("/place/recommend")
    String getPlaceRecommendation(@RequestParam Long accountId);

    @GetMapping("/place/keywords")
    List<String> getPlacesByKeywords(@RequestParam("keywords") List<String> keywords);

    @GetMapping("/place/keyword")
    List<PlaceResponseDto> getPlacesByKeyword(@RequestParam("keyword") String keyword);

    @GetMapping("/place/location")
    List<PlaceResponseDto> getPlacesByLocation(@RequestParam("location") String location);
    */
}
