package com.example.place_data.controller.client;

import com.example.place_data.controller.response.IdAccountResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "account")
public interface AccountClient {
    @GetMapping("/account/find-id")
    IdAccountResponse getAccountId(@RequestHeader("Authorization") String token);
}
