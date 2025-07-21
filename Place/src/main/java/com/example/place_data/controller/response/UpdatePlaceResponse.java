package com.example.place_data.controller.response;

import com.example.place_data.entity.Place;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class UpdatePlaceResponse {
    private String title;
    private String content;
    private String category;
    private String location;
    private String address;

    public UpdatePlaceResponse(){

    }

    public UpdatePlaceResponse(String title, String content, String category, String location, String address) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.location = location;
        this.address = address;
    }

    public static UpdatePlaceResponse from(Place place) {
        return new UpdatePlaceResponse(
                place.getTitle(),
                place.getContent(),
                place.getCategory(),
                place.getLocation(),
                place.getAddress()
        );
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
