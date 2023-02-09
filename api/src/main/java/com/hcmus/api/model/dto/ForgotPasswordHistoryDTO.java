package com.hcmus.api.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ForgotPasswordHistoryDTO {
    @JsonProperty(value = "user_id")
    private Long userId;

    @JsonProperty(value = "reset_at")
    private Long resetAt;

    @JsonProperty(value = "otp_code")
    private String otpCode;

    @JsonProperty(value = "old_password")
    private String oldPassword;

    @JsonProperty(value = "new_password")
    private String newPassword;
}
