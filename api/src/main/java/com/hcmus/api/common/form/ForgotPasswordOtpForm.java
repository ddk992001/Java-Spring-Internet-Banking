package com.hcmus.api.common.form;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ForgotPasswordOtpForm {
    private final String otpCode;
    private final Long userId;

    @JsonCreator
    public ForgotPasswordOtpForm(@JsonProperty("otp") String otpCode, @JsonProperty("user_id") Long userId) {
        this.otpCode = otpCode;
        this.userId = userId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public String getOtpCode() {
        return this.otpCode;
    }
}
