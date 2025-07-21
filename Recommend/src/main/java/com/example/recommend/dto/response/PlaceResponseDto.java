package com.example.recommend.dto.response;

import lombok.Data;

@Data
public class PlaceResponseDto {
    private Long place_id;
    private String title;     // 제목
    private String content;   // 내용 요약
    private String category;  // 테마 (HEALING, NIGHTLIFE 등)
    private String location;  // 지역
    private String address;
//    public PlaceResponseDto(Long placeId, String title, String content, String category, String location) {
//        this.placeId = placeId;
//        this.title = title;
//        this.content = content;
//        this.category = category;
//        this.location = location;
//    }


    public PlaceResponseDto(Long place_id, String title, String content, String category, String location, String address) {
        this.place_id = place_id;
        this.title = title;
        this.content = content;
        this.category = category;
        this.location = location;
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getCategory() {
        return category;
    }

    public String getLocation() {
        return location;
    }

    public String getAddress() {
        return address;
    }

}