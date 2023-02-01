package com.hcmus.api.controller;

import com.hcmus.api.common.form.ChangePasswordMainForm;
import com.hcmus.api.common.form.ForgotPasswordEmailForm;
import com.hcmus.api.common.form.ForgotPasswordMainForm;
import com.hcmus.api.common.form.ForgotPasswordOtpForm;
import com.hcmus.api.common.form.LoginForm;
import com.hcmus.api.common.response.ForgotPasswordEmailValidationResponse;
import com.hcmus.api.common.response.ForgotPasswordOtpValidationResponse;
import com.hcmus.api.common.response.Response;
import com.hcmus.api.exception.GenericException;
import com.hcmus.api.model.dto.UserAccountDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserAccountController {
    ResponseEntity<UserAccountDTO> login(@RequestBody LoginForm loginForm) throws GenericException;
    ResponseEntity<ForgotPasswordEmailValidationResponse> validateEmail(@RequestBody ForgotPasswordEmailForm emailForm) throws GenericException;
    ResponseEntity<ForgotPasswordOtpValidationResponse> validateOtp(@RequestBody ForgotPasswordOtpForm otpForm) throws GenericException;
    ResponseEntity<Response> resetPassword(@RequestBody ForgotPasswordMainForm mainForm) throws GenericException;
    ResponseEntity<Response> changePassword(@RequestBody ChangePasswordMainForm mainForm, @PathVariable String username) throws GenericException;
}
