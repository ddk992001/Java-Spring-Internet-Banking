package com.hcmus.api.repository;

import com.hcmus.api.model.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, String> {
    @Query
    Optional<UserAccount> findByUserId(Long userId);
}