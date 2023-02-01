package com.hcmus.api.common.form;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ChangePasswordMainForm {
    private final String oldPassword;
    private final String newPassword;

    @JsonCreator
    public ChangePasswordMainForm(@JsonProperty("old_password") String oldPassword, @JsonProperty("new_password") String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return this.oldPassword;
    }

    public String getNewPassword() {
        return this.newPassword;
    }
}
