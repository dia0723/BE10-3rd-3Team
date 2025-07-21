package com.example.recommend.dto.request;

import lombok.Data;

@Data
public class PlaceSearchRequest {
    private String title;     // 검색어 키워드
    private String category;  // 카테고리
    private String location;  // 지역

    public PlaceSearchRequest() {
    }
    public PlaceSearchRequest(String title, String category, String location) {
        this.title = title;
        this.category = category;
        this.location = location;
    }
}