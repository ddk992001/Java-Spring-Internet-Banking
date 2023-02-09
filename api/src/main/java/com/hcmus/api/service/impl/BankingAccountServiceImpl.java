package com.hcmus.api.service.impl;

import com.hcmus.api.common.response.Response;
import com.hcmus.api.common.variables.ExceptionType;
import com.hcmus.api.common.variables.FailedOperation;
import com.hcmus.api.exception.GenericException;
import com.hcmus.api.model.dto.BankingAccountDTO;
import com.hcmus.api.model.entity.BankingAccount;
import com.hcmus.api.model.mapper.impl.BankingAccountMapper;
import com.hcmus.api.repository.BankingAccountRepository;
import com.hcmus.api.service.BankingAccountService;
import com.hcmus.api.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service(value = "bankingAccountService")
public class BankingAccountServiceImpl implements GenericService<BankingAccountDTO, String>, BankingAccountService {
    @Autowired
    private BankingAccountRepository bankingAccountRepository;

    @Autowired
    @Qualifier(value = "bankingAccountMapper")
    private BankingAccountMapper bankingAccountMapper;

    @Override
    public List<BankingAccountDTO> getAll() {
        return null;
    }

    @Override
    public BankingAccountDTO getById(String accountNumber) throws GenericException {
        Optional<BankingAccount> bankingAccountOptional = bankingAccountRepository.findById(accountNumber);

        if (bankingAccountOptional.isEmpty())
            throw new GenericException(FailedOperation.NOT_EXISTED_BANKING_ACCOUNT, ExceptionType.COMMON_EXCEPTION);

        return bankingAccountMapper.convertToDTO(bankingAccountOptional.get());
    }

    @Override
    public void create(BankingAccountDTO object) {

    }

    @Override
    public void update(String id, BankingAccountDTO object) throws GenericException {

    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public void deleteAll() {

    }

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
