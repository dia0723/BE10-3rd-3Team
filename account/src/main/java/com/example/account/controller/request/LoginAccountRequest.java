package com.example.account.controller.request;

import lombok.*;

public class LoginAccountRequest {
    private String userId;
    private String password;
    public LoginAccountRequest(){}

    public LoginAccountRequest(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

}
