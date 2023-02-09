package com.hcmus.api.common.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ForgotPasswordEmailValidationResponse extends Response {
    @JsonProperty(value = "user_id")
    private final Long userId;

    public ForgotPasswordEmailValidationResponse(String message, int code, boolean status, Long userId) {
        super(message, code, status);
        this.userId = userId;
    }

    public Long getUserId() {
        return this.userId;
    }
}
