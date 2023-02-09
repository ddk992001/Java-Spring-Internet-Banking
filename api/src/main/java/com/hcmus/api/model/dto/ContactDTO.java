package com.hcmus.api.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContactDTO {
    @JsonProperty(value = "user_id")
    private Long userId;

    @JsonProperty(value = "account_number")
    private String accountNumber;

    @JsonProperty(value = "nick_name")
    private String nickName;

    @JsonProperty(value = "bank_code")
    private String bankCode;
}
