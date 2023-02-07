package com.hcmus.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContactDTO {
    private Long userId;
    private String accountNumber;
    private String nickName;
    private String bankCode;
}
