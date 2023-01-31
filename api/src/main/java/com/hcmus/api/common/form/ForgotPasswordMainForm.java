package com.hcmus.api.common.form;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ForgotPasswordMainForm {
    private final String password;
    private final String resetPasswordToken;
    private final Long userId;

    @JsonCreator
    public ForgotPasswordMainForm(@JsonProperty("password") String password, @JsonProperty("reset_password_token") String resetPasswordToken, @JsonProperty("user_id") Long userId) {
        this.password = password;
        this.resetPasswordToken = resetPasswordToken;
        this.userId = userId;
    }

    public String getPassword() {
        return this.password;
    }

    public Long getUserId() {
        return this.userId;
    }

    public String getResetPasswordToken() {
        return this.resetPasswordToken;
    }
}
