package com.hcmus.api.service;

import com.hcmus.api.model.dto.BankingAccountDTO;

import java.util.List;

public interface BankingAccountService {
    List<BankingAccountDTO> getAllByUserId(Long userId);
    List<BankingAccountDTO> getAllByUserIdAndType(Long userId, int isSpendAccount);
}
