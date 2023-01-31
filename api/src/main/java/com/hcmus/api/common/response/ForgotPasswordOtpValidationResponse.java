package com.hcmus.api.common.response;

public class ForgotPasswordOtpValidationResponse extends Response {
    private final String resetPasswordToken;
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
