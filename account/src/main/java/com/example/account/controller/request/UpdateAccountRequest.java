package com.example.account.controller.request;

public class UpdateAccountRequest {
    private String currentPassword;
    private String newPassword;
    private String newNickName;

    public UpdateAccountRequest() {
    }

    public UpdateAccountRequest(String currentPassword, String newPassword, String newNickName) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
        this.newNickName = newNickName;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewNickName() {
        return newNickName;
    }

    public void setNewNickName(String newNickName) {
        this.newNickName = newNickName;
    }
}
