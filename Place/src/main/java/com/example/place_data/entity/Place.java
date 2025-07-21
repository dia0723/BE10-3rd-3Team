package com.example.place_data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor


public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long place_id;

    private String title;
    private String content;
    private String category;
    private String location;
    private String address;
    private String message;

    private Long accountId;

    public Place(String title, String content, String category, String location, String address) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.location = location;
        this.address = address;
    }

    public Place(String title, String content, String category, String location, String address, Long accountId) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.location = location;
        this.address = address;
        this.accountId = accountId;
    }
    public Long getPlace_id() {
        return place_id;
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

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
