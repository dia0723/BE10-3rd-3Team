package com.example.place_data.controller.request;

import lombok.*;

@ToString
public class UpdatePlaceWithAuthorizationRequest {
    private Long place_id;
    private String title;
    private String content;
    private String category;
    private String location;
    private String address;

    public UpdatePlaceWithAuthorizationRequest() {
    }

    public UpdatePlaceWithAuthorizationRequest(Long place_id, String title, String content, String category, String location, String address) {
        this.place_id = place_id;
        this.title = title;
        this.content = content;
        this.category = category;
        this.location = location;
        this.address = address;
    }

    public Long getPlace_id() {
        return place_id;
    }

    public void setPlace_id(Long place_id) {
        this.place_id = place_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
