package com.example.place_data.controller.request;
import lombok.*;

@ToString
public class PlaceSearchRequest {
    private String title;
    private String category;
    private String location;
    
    public PlaceSearchRequest(){}

    public PlaceSearchRequest(String title, String category, String location) {
        this.title = title;
        this.category = category;
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
}
