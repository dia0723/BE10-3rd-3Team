package com.example.account.controller.response;

public class LoginAccountResponse {
    private String userToken;
    private String message;

    public LoginAccountResponse() {
    }

    public LoginAccountResponse(String message) {
        this.message = message;
    }

    public static LoginAccountResponse from(String userToken, String message) {
        return new LoginAccountResponse(userToken, message);
    }

    public LoginAccountResponse(String userToken, String message) {
        this.userToken = userToken;
        this.message = message;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
