package com.hcmus.api.controller.impl;

import com.hcmus.api.controller.BankingAccountController;
import com.hcmus.api.model.dto.BankingAccountDTO;
import com.hcmus.api.service.impl.BankingAccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/bankingAccounts")
public class BankingAccountControllerImpl implements BankingAccountController {
    @Autowired
    @Qualifier(value = "bankingAccountService")
    private BankingAccountServiceImpl bankingAccountService;

    @GetMapping
    @Override
    public ResponseEntity<List<BankingAccountDTO>> getAllByUserIdAndType(@PathVariable Long userId, @RequestParam(required = false) Integer isSpendAccount) {
        List<BankingAccountDTO> bankingAccounts;

        if (isSpendAccount == null)
            bankingAccounts = bankingAccountService.getAllByUserId(userId);
        else
            bankingAccounts = bankingAccountService.getAllByUserIdAndType(userId, isSpendAccount);

        return ResponseEntity.ok(bankingAccounts);
    }
}
