package com.hcmus.api.model.entity;

import com.hcmus.api.model.entity.key.ForgotPasswordId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "forgot_password_history")
@AllArgsConstructor
@NoArgsConstructor
public class ForgotPasswordHistory {
    @EmbeddedId
    private ForgotPasswordId forgotPasswordId;

    @Column(name = "otp_code")
    private String otpCode;

    @Column(name = "old_password")
    private String oldPassword;

    @Column(name = "new_password")
    private String newPassword;
}
