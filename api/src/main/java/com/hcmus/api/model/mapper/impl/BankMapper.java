package com.hcmus.api.model.mapper.impl;

import com.hcmus.api.model.dto.BankDTO;
import com.hcmus.api.model.entity.Bank;
import com.hcmus.api.model.mapper.GenericMapper;
import org.springframework.stereotype.Component;

@Component(value = "bankMapper")
public class BankMapper implements GenericMapper<Bank, BankDTO> {
    @Override
    public Bank convertToEntity(BankDTO object) {
        return null;
    }

    @Override
    public BankDTO convertToDTO(Bank bank) {
        return new BankDTO(bank.getBankCode(), bank.getBankName());
    }
}
