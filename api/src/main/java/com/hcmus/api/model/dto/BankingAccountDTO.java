package com.hcmus.api.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BankingAccountDTO {
    @JsonProperty(value = "account_number")
    private String accountNumber;

    private Long balance;

    @JsonProperty(value = "user_id")
    private Long userId;

    @JsonProperty(value = "bank_code")
    private String bankCode;

    @JsonProperty(value = "is_spend_account")
    private boolean isSpendAccount;
}
