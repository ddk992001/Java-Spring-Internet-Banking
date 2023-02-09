package com.hcmus.api.controller;

import com.hcmus.api.common.form.ChangePasswordMainForm;
import com.hcmus.api.common.form.ForgotPasswordEmailForm;
import com.hcmus.api.common.form.ForgotPasswordMainForm;
import com.hcmus.api.common.form.ForgotPasswordOtpForm;
import com.hcmus.api.common.form.LoginForm;
import com.hcmus.api.common.response.ForgotPasswordEmailValidationResponse;
import com.hcmus.api.common.response.ForgotPasswordOtpValidationResponse;
import com.hcmus.api.common.response.Response;
import com.hcmus.api.model.dto.UserAccountDTO;
import org.springframework.http.ResponseEntity;

public interface UserAccountController {
    ResponseEntity<UserAccountDTO> login(LoginForm loginForm);
    ResponseEntity<ForgotPasswordEmailValidationResponse> validateEmail(ForgotPasswordEmailForm emailForm);
    ResponseEntity<ForgotPasswordOtpValidationResponse> validateOtp(ForgotPasswordOtpForm otpForm);
    ResponseEntity<Response> resetPassword(ForgotPasswordMainForm mainForm);
    ResponseEntity<Response> changePassword(ChangePasswordMainForm mainForm, String username);
}
