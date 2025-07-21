package com.example.place_data.controller.response;

import com.example.place_data.entity.Place;

public class RegisterPlaceWithAuthorizationResponse {

    private Long place_id;
    private String message;

    RegisterPlaceWithAuthorizationResponse() {
    }

    public RegisterPlaceWithAuthorizationResponse(Long place_id, String message) {
        this.place_id = place_id;
        this.message = message;
    }

    public Long getPlace_id() {
        return place_id;
    }

    public void setPlace_id(Long place_id) {
        this.place_id = place_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static RegisterPlaceWithAuthorizationResponse from(Place place, String message) {
        return new RegisterPlaceWithAuthorizationResponse(
                place.getPlace_id(),
                message
        );
    }
}
