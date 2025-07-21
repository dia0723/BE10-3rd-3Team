package com.example.account.controller.response;

import com.example.account.entity.Account;

public class MyProfileAccountResponse {
    private String userId;
    private String nickName;

    public MyProfileAccountResponse() {
    }

    public MyProfileAccountResponse(String userId, String nickName) {
        this.userId = userId;
        this.nickName = nickName;
    }

    public static MyProfileAccountResponse from(Account account) {
        return new MyProfileAccountResponse(
                account.getUserId(),
                account.getNickName());
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}

