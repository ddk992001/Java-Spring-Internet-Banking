package com.hcmus.api.repository;

import com.hcmus.api.model.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, String> {

}
