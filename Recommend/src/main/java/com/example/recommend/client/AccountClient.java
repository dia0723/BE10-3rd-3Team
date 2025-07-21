package com.example.recommend.client;

import com.example.recommend.dto.response.IdAccountResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "account")
public interface AccountClient {
    @GetMapping("/account/find-id")
    IdAccountResponseDto getAccountId(@RequestHeader("Authorization") String token);
}
