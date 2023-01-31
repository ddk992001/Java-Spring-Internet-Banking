package com.hcmus.api.common.form;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ForgotPasswordEmailForm {
    private final String email;

    @JsonCreator
    public ForgotPasswordEmailForm(@JsonProperty("email") String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }
}
