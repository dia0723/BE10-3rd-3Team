package com.example.place_data.controller.request;

import com.example.place_data.entity.Place;
import lombok.*;

@ToString
public class RegisterPlaceWithAuthorizationRequest {
    private String title;
    private String content;
    private String category;
    private String location;
    private String address;

    public RegisterPlaceWithAuthorizationRequest(){}

    public RegisterPlaceWithAuthorizationRequest(String title, String content, String category, String location, String address) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.location = location;
        this.address = address;
    }

    public Place toRegister(Long accountId) {
        Place place = new Place();
        place.setTitle(this.title);
        place.setContent(this.content);
        place.setCategory(this.category);
        place.setLocation(this.location);
        place.setAddress(this.address);
        place.setAccountId(accountId);
        return place;
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
