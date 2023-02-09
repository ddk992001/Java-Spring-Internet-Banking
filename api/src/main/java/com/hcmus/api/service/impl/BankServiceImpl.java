package com.hcmus.api.service.impl;

import com.hcmus.api.common.variables.ExceptionType;
import com.hcmus.api.common.variables.FailedOperation;
import com.hcmus.api.exception.GenericException;
import com.hcmus.api.model.dto.BankDTO;
import com.hcmus.api.model.entity.Bank;
import com.hcmus.api.model.mapper.impl.BankMapper;
import com.hcmus.api.repository.BankRepository;
import com.hcmus.api.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service(value = "bankService")
public class BankServiceImpl implements GenericService<BankDTO, String> {
    @Autowired
    private BankRepository bankRepository;

    @Autowired
    @Qualifier(value = "bankMapper")
    private BankMapper bankMapper;

    @Override
    public List<BankDTO> getAll() {
        return null;
    }

    @Override
    public BankDTO getById(String bankCode) throws GenericException {
        Optional<Bank> bankOptional = bankRepository.findById(bankCode);

        if (bankOptional.isEmpty())
            throw new GenericException(FailedOperation.NOT_EXISTED_BANK, ExceptionType.COMMON_EXCEPTION);

        return bankMapper.convertToDTO(bankOptional.get());
    }

    @Override
    public void create(BankDTO object) {

    }

    @Override
    public void update(String id, BankDTO object) {

    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public void deleteAll() {

    }
}
