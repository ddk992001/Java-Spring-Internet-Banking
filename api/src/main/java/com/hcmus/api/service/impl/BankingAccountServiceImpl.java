package com.hcmus.api.service.impl;

import com.hcmus.api.exception.GenericException;
import com.hcmus.api.model.dto.BankingAccountDTO;
import com.hcmus.api.model.entity.BankingAccount;
import com.hcmus.api.model.mapper.impl.BankingAccountMapper;
import com.hcmus.api.repository.BankingAccountRepository;
import com.hcmus.api.service.BankingAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "bankingAccountService")
public class BankingAccountServiceImpl implements BankingAccountService {
    @Autowired
    private BankingAccountRepository bankingAccountRepository;

    @Autowired
    @Qualifier(value = "bankingAccountMapper")
    private BankingAccountMapper bankingAccountMapper;

    @Override
    public List<BankingAccountDTO> getAllByUserId(Long userId) {
        List<BankingAccount> bankingAccounts = bankingAccountRepository.findByUserId(userId);
        return bankingAccounts
                .stream()
                .map(bankingAccount -> bankingAccountMapper.convertToDTO(bankingAccount))
                .toList();
    }

    @Override
    public List<BankingAccountDTO> getAllByUserIdAndType(Long userId, int isSpendAccount) {
        List<BankingAccount> bankingAccounts = bankingAccountRepository.findByUserIdAndIsSpendAccount(userId, isSpendAccount);
        return bankingAccounts
                .stream()
                .map(bankingAccount -> bankingAccountMapper.convertToDTO(bankingAccount))
                .toList();
    }
}
