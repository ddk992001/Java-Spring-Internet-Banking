package com.hcmus.api.service;

import com.hcmus.api.common.response.ForgotPasswordEmailValidationResponse;
import com.hcmus.api.common.response.ForgotPasswordOtpValidationResponse;
import com.hcmus.api.common.response.Response;
import com.hcmus.api.exception.GenericException;
import com.hcmus.api.model.dto.UserAccountDTO;

public interface UserAccountService {
    UserAccountDTO authenticateAccount(String username, String password) throws GenericException;
    ForgotPasswordEmailValidationResponse validateEmail(String email) throws GenericException;
    ForgotPasswordOtpValidationResponse validateOtp(String otpCode, Long userId) throws GenericException;
    Response resetPassword(String newPassword, String resetPasswordToken, Long userId) throws GenericException;
    Response changePassword(String username, String oldPassword, String newPassword) throws GenericException;
}
