package com.example.account.controller;

import com.example.account.controller.request.DeleteAccountRequest;
import com.example.account.controller.request.RegisterAccountRequest;
import com.example.account.controller.request.UpdateAccountRequest;
import com.example.account.controller.response.*;
import com.example.account.controller.request.LoginAccountRequest;
import com.example.account.entity.Account;
import com.example.account.redis_cache.RedisCacheService;
import com.example.account.repository.AccountRepository;
import com.example.account.utility.EncryptionUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    RedisCacheService redisCacheService;

    @PostMapping("/register")
    public RegisterAccountResponse register(@RequestBody RegisterAccountRequest request){
        log.info("register -> RegisterAccountRequest: {}", request);
        Account registerAccount = request.toAccount();
        Account createdAccount = accountRepository.save(registerAccount);

        return RegisterAccountResponse.from(createdAccount);
    }

    @PostMapping("/login")
    public LoginAccountResponse login(@RequestBody LoginAccountRequest request){
        log.info("login -> LoginRequest : {}", request);
        String requestUserId = request.getUserId();
        Optional<Account> maybeAccount = accountRepository.findByUserId(requestUserId);

        if(maybeAccount.isEmpty()){
            return new LoginAccountResponse("아이디와 비밀번호가 틀렸습니다.");
        }
        Account account = maybeAccount.get();
        String requestPassword = request.getPassword();

        String nickName = account.getNickName();
        String message = nickName + "으로 로그인 성공하셨습니다!";

        boolean matched = EncryptionUtility.matches(requestPassword, account.getPassword());
        if(!matched){
            return new LoginAccountResponse("아이디와 비밀번호가 틀렸습니다.");
        }

        String token = UUID.randomUUID().toString();
        redisCacheService.setKeyAndValue(token, account.getId(), Duration.ofDays(1));
        return LoginAccountResponse.from(token, message);
    }
    @GetMapping("/find-id")
    public ResponseEntity<IdAccountResponse> getAccountId(@RequestHeader("Authorization") String token){
        String pureToken = extractToken(token);
        String accountId = redisCacheService.getValueByKey(pureToken, String.class);

        if(accountId == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Account account = accountRepository.findById(Long.parseLong(accountId))
                .orElseThrow(()-> new RuntimeException("사용자가 존재하지 않음"));

        return ResponseEntity.ok(new IdAccountResponse(account.getId()));
    }

    private String extractToken(String token){
        if(token != null && token.startsWith("Bearer ")){
            return token.substring(7);
        }
        return token;
    }

    @PostMapping("/delete")
    public ResponseEntity<DeleteAccountResponse> delete (
            @RequestHeader("Authorization") String token, @RequestBody DeleteAccountRequest request){
        // 로그인 되었는지 확인
        String pureToken = extractToken(token);
        String accountId = redisCacheService.getValueByKey(pureToken, String.class);
        if(accountId == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Account account = accountRepository.findById(Long.parseLong(accountId))
                .orElseThrow(()-> new RuntimeException("사용자가 존재하지 않음"));
        System.out.println("헤더 토큰: " + token);
        // 비밀번호 확인(본인이 맞는지)
        String requestPassword = request.getPassword();
        boolean matched = EncryptionUtility.matches(requestPassword, account.getPassword());
        if(!matched){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new DeleteAccountResponse("비밀번호가 틀렸습니다."));
        }
        accountRepository.delete(account);
        redisCacheService.deleteByKey(pureToken);

        return ResponseEntity.ok(new DeleteAccountResponse("회원 탈퇴가 완료되었습니다."));
    }

    @GetMapping("/my-profile")
    public ResponseEntity<?> getMyProfile(
            @RequestHeader("Authorization")String token){
        String pureToken = extractToken(token);
        String accountId = redisCacheService.getValueByKey(pureToken, String.class);

        if(accountId == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message","로그인이 안된 상태에서는 접근이 제한됩니다."));
        }
        Account account = accountRepository.findById(Long.parseLong(accountId))
                .orElseThrow(()-> new RuntimeException("사용자가 존재하지 않음"));

        return ResponseEntity.ok(MyProfileAccountResponse.from(account));
    }

    // 사용자 정보 수정
    @PostMapping("/update")
    public ResponseEntity<UpdateAccountResponse> update (
            @RequestHeader("Authorization") String token,
            @RequestBody UpdateAccountRequest request){
        String pureToken = extractToken(token);
        String accountId = redisCacheService.getValueByKey(pureToken, String.class);

        if(accountId == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Account account = accountRepository.findById(Long.parseLong(accountId))
                .orElseThrow(()-> new RuntimeException("사용자가 존재하지 않음"));

        // 기존 비밀번호 검증
        if(!EncryptionUtility.matches(request.getCurrentPassword(), account.getPassword())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new UpdateAccountResponse("현재 비밀번호가 일치하지 않습니다."));
        }
        // 새 비밀번호가 기존과 같은지 검증
        if(request.getNewPassword().equals(request.getCurrentPassword())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new UpdateAccountResponse("이전 비밀번호와 동일한 비밀번호로 변경할 수 없습니다."));
        }
        // 수정
        account.setPassword(EncryptionUtility.encode(request.getNewPassword()));
        account.setNickName(request.getNewNickName());
        accountRepository.save(account);

        return  ResponseEntity.ok(new UpdateAccountResponse("회원 정보가 성공적으로 수정되었습니다."));
    }



}
