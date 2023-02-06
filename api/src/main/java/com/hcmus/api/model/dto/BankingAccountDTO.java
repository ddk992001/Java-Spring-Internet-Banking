package com.hcmus.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BankingAccountDTO {
    private String accountNumber;
    private Long balance;
    private Long userId;
    private String bankCode;
    private boolean isSpendAccount;
}
