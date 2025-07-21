package com.example.review.client;


import com.example.review.controller.response.IdAccountResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "account")
public interface AccountClient {

    @GetMapping("/account/find-id")
    IdAccountResponse getAccountId(@RequestHeader("Authorization") String token);

}
