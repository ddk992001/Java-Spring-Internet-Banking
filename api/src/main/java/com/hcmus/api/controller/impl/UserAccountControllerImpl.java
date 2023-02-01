package com.hcmus.api.controller.impl;

import com.hcmus.api.common.form.ChangePasswordMainForm;
import com.hcmus.api.common.form.ForgotPasswordEmailForm;
import com.hcmus.api.common.form.ForgotPasswordMainForm;
import com.hcmus.api.common.form.ForgotPasswordOtpForm;
import com.hcmus.api.common.form.LoginForm;
import com.hcmus.api.common.response.ForgotPasswordEmailValidationResponse;
import com.hcmus.api.common.response.ForgotPasswordOtpValidationResponse;
import com.hcmus.api.common.response.Response;
import com.hcmus.api.controller.UserAccountController;
import com.hcmus.api.exception.GenericException;
import com.hcmus.api.model.dto.UserAccountDTO;
import com.hcmus.api.service.impl.UserAccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class UserAccountControllerImpl implements UserAccountController {
    @Autowired
    private UserAccountServiceImpl userAccountService;

    @PostMapping("/authentication")
    @Override
    public ResponseEntity<UserAccountDTO> login(@RequestBody LoginForm loginForm) throws GenericException {
        UserAccountDTO userAccountDTO = userAccountService.authenticateAccount(loginForm.getUsername(), loginForm.getPassword());
        return ResponseEntity.ok(userAccountDTO);
    }

    @PostMapping("/password/otp")
    @Override
    public ResponseEntity<ForgotPasswordEmailValidationResponse> validateEmail(@RequestBody ForgotPasswordEmailForm emailForm) throws GenericException {
        ForgotPasswordEmailValidationResponse emailValidationResponse = userAccountService.validateEmail(emailForm.getEmail());
        return ResponseEntity.ok(emailValidationResponse);
    }

    @PostMapping("/password/validation/otp")
    @Override
    public ResponseEntity<ForgotPasswordOtpValidationResponse> validateOtp(@RequestBody ForgotPasswordOtpForm otpForm) throws GenericException {
        ForgotPasswordOtpValidationResponse otpValidationResponse = userAccountService.validateOtp(otpForm.getOtpCode(), otpForm.getUserId());
        return ResponseEntity.ok(otpValidationResponse);
    }

    @PostMapping("/password")
    @Override
    public ResponseEntity<Response> resetPassword(@RequestBody ForgotPasswordMainForm mainForm) throws GenericException {
        Response resetPasswordResponse = userAccountService.resetPassword(mainForm.getPassword(), mainForm.getResetPasswordToken(), mainForm.getUserId());
        return ResponseEntity.ok(resetPasswordResponse);
    }

    @PutMapping("/{username}/password")
    @Override
    public ResponseEntity<Response> changePassword(@RequestBody ChangePasswordMainForm mainForm, @PathVariable String username) throws GenericException {
        Response changePasswordResponse = userAccountService.changePassword(username, mainForm.getOldPassword(), mainForm.getNewPassword());
        return ResponseEntity.ok(changePasswordResponse);
    }
}
