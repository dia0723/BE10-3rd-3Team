package com.example.account.controller.response;

public class DeleteAccountResponse {
    private String message;

    public DeleteAccountResponse(String message) {
        this.message = message;
    }

    public DeleteAccountResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
