package com.hcmus.api.repository;

import com.hcmus.api.model.entity.ForgotPasswordHistory;
import com.hcmus.api.model.entity.key.ForgotPasswordId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ForgotPasswordHistoryRepository extends JpaRepository<ForgotPasswordHistory, ForgotPasswordId> {
    @Query
    List<ForgotPasswordHistory> findByForgotPasswordIdUserId(Long userId);
}
