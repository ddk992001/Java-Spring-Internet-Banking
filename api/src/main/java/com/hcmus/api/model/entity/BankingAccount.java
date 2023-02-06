package com.hcmus.api.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "banking_account")
public class BankingAccount {
    @Id
    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "balance")
    private Long balance;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "bank_code")
    private String bankCode;

    @Column(name = "is_spend_account")
    private Integer isSpendAccount;
}
