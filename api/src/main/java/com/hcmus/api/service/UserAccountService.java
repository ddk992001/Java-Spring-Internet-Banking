package com.hcmus.api.service;

import com.hcmus.api.common.response.ForgotPasswordEmailValidationResponse;
import com.hcmus.api.common.response.ForgotPasswordOtpValidationResponse;
import com.hcmus.api.common.response.Response;
import com.hcmus.api.model.dto.UserAccountDTO;

public interface UserAccountService {
    UserAccountDTO authenticateAccount(String username, String password);
    ForgotPasswordEmailValidationResponse validateEmail(String email);
    ForgotPasswordOtpValidationResponse validateOtp(String otpCode, Long userId);
    Response resetPassword(String newPassword, String resetPasswordToken, Long userId);
    Response changePassword(String username, String oldPassword, String newPassword);
}
