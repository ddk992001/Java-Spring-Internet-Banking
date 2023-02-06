package com.hcmus.api.repository;

import com.hcmus.api.model.entity.BankingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BankingAccountRepository extends JpaRepository<BankingAccount, String> {
    @Query
    List<BankingAccount> findByUserId(Long userId);

    @Query
    List<BankingAccount> findByUserIdAndIsSpendAccount(Long userId, Integer isSpendAccount);
}
