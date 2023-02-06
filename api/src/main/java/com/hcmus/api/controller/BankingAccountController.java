package com.hcmus.api.controller;

import com.hcmus.api.model.dto.BankingAccountDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BankingAccountController {
    ResponseEntity<List<BankingAccountDTO>> getAllByUserIdAndType(Long userId, Integer isSpendAccount);
}
