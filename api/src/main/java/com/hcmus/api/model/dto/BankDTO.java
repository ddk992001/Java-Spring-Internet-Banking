package com.hcmus.api.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BankDTO {
    @JsonProperty(value = "bank_code")
    private String bankCode;

    @JsonProperty(value = "bank_name")
    private String bankName;
}
