package com.example.account.repository;

import com.example.account.entity.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Long> {
    Optional<Account> findByUserId(String requestUserId);
}
