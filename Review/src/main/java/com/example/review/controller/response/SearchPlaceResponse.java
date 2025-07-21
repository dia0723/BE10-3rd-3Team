package com.example.review.controller.response;

public class SearchPlaceResponse {

    private Long placeId;

    public SearchPlaceResponse(){}

    public SearchPlaceResponse(Long placeId) {
        this.placeId = placeId;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }
}
