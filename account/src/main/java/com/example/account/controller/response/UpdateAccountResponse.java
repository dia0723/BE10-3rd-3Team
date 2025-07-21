package com.example.account.controller.response;

public class UpdateAccountResponse {
    private String message;

    public UpdateAccountResponse() {
    }

    public UpdateAccountResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
