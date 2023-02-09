package com.hcmus.api.common.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ForgotPasswordOtpValidationResponse extends Response {
    @JsonProperty(value = "reset_password_token")
    private final String resetPasswordToken;

    @JsonProperty(value = "user_id")
    private final Long userId;

    public ForgotPasswordOtpValidationResponse(String message, int code, boolean status, String resetPasswordToken, Long userId) {
        super(message, code, status);
        this.resetPasswordToken = resetPasswordToken;
        this.userId = userId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public String getResetPasswordToken() {
        return this.resetPasswordToken;
    }
}
