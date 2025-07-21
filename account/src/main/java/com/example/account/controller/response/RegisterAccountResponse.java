package com.example.account.controller.response;

import com.example.account.entity.Account;

public class RegisterAccountResponse {
    private String message;

    public RegisterAccountResponse() {
    }

    public RegisterAccountResponse(String message) {
        this.message = message;
    }
    public static RegisterAccountResponse from(Account account) {
        if (account == null) {
            return new RegisterAccountResponse("회원가입에 실패하였습니다.");
        }
        return new RegisterAccountResponse("회원가입에 성공하였습니다.");
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
