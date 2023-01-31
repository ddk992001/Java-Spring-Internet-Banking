package com.hcmus.api.common.response;

public class ForgotPasswordEmailValidationResponse extends Response {
    private final Long userId;

    public ForgotPasswordEmailValidationResponse(String message, int code, boolean status, Long userId) {
        super(message, code, status);
        this.userId = userId;
    }

    public Long getUserId() {
        return this.userId;
    }
}
