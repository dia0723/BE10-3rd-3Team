package com.example.account.controller.request;

import com.example.account.entity.Account;
import com.example.account.utility.EncryptionUtility;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RegisterAccountRequest {
    private String userId;
    private String password;
    private String nickName;

    public Account toAccount() {
        return new Account(userId, EncryptionUtility.encode(password), nickName);
    }
}
