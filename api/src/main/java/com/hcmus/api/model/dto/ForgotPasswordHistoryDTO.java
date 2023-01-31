package com.hcmus.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ForgotPasswordHistoryDTO {
    private Long userId;
    private Long resetAt;
    private String otpCode;
    private String oldPassword;
    private String newPassword;
}
