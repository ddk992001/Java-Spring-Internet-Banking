package com.hcmus.api.model.mapper.impl;

import com.hcmus.api.model.dto.BankingAccountDTO;
import com.hcmus.api.model.entity.BankingAccount;
import com.hcmus.api.model.mapper.GenericMapper;
import org.springframework.stereotype.Component;

@Component(value = "bankingAccountMapper")
public class BankingAccountMapper implements GenericMapper<BankingAccount, BankingAccountDTO> {
    @Override
    public BankingAccount convertToEntity(BankingAccountDTO object) {
        return null;
    }

    @Override
    public BankingAccountDTO convertToDTO(BankingAccount bankingAccount) {
        boolean isSpendAccount = bankingAccount.getIsSpendAccount() == 1;
        return new BankingAccountDTO(bankingAccount.getAccountNumber(), bankingAccount.getBalance(), bankingAccount.getUserId(), bankingAccount.getBankCode(), isSpendAccount);
    }
}
