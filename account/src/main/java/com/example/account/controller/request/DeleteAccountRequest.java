package com.example.account.controller.request;

public class DeleteAccountRequest {
    private String password;

    public DeleteAccountRequest(String password) {
        this.password = password;
    }

    public DeleteAccountRequest() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
