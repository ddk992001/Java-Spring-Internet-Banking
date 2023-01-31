package com.hcmus.api.service.impl;

import com.hcmus.api.common.response.ForgotPasswordOtpValidationResponse;
import com.hcmus.api.common.utils.DateUtils;
import com.hcmus.api.common.utils.MailUtils;
import com.hcmus.api.common.utils.OtpUtils;
import com.hcmus.api.common.variables.FailedOperation;
import com.hcmus.api.common.utils.JwtUtils;
import com.hcmus.api.common.utils.BcryptUtils;
import com.hcmus.api.common.response.Response;
import com.hcmus.api.common.variables.Mail;
import com.hcmus.api.common.variables.SuccessfulOperation;
import com.hcmus.api.common.response.ForgotPasswordEmailValidationResponse;
import com.hcmus.api.common.variables.Time;
import com.hcmus.api.exception.GenericException;
import com.hcmus.api.model.dto.ForgotPasswordHistoryDTO;
import com.hcmus.api.model.dto.UserAccountDTO;
import com.hcmus.api.model.dto.UserDTO;
import com.hcmus.api.model.entity.UserAccount;
import com.hcmus.api.model.entity.key.ForgotPasswordId;
import com.hcmus.api.model.mapper.impl.UserAccountMapper;
import com.hcmus.api.repository.UserAccountRepository;
import com.hcmus.api.service.UserAccountService;
import com.hcmus.api.service.impl.email.EmailDetail;
import com.hcmus.api.service.impl.email.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserAccountServiceImpl implements UserAccountService {
    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    @Qualifier(value = "userAccountMapper")
    private UserAccountMapper userAccountMapper;

    @Autowired
    @Qualifier(value = "userTypeService")
    private UserTypeServiceImpl userTypeService;

    @Autowired
    @Qualifier(value = "userService")
    private UserServiceImpl userService;

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    @Qualifier(value = "forgotPasswordHistoryService")
    private ForgotPasswordHistoryServiceImpl forgotPasswordHistoryService;

    @Override
    public UserAccountDTO authenticateAccount(String username, String password) throws GenericException {
        Optional<UserAccount> userAccountOptional = userAccountRepository.findById(username);

        if (userAccountOptional.isEmpty() || !BcryptUtils.isSameCode(password, userAccountOptional.get().getHashedPassword()))
            throw new GenericException(FailedOperation.LOGIN_FAILED);

        UserAccount userAccount = userAccountOptional.get();
        SuccessfulOperation successfulOperation = SuccessfulOperation.LOGIN_SUCCESSFULLY;
        String accessToken = JwtUtils.createAccessToken(username);
        UserAccountDTO userAccountDTO = userAccountMapper.convertToDTO(userAccount);

        userAccount.setLastExpiredAt(DateUtils.convertLocalDateTimeToTimeStamp(LocalDateTime.now().plusHours(Time.ACCESS_TOKEN_EXPIRE_AFTER_HOUR)));
        userAccountRepository.saveAndFlush(userAccount);

        userAccountDTO.setResponseInfo(new Response(successfulOperation.getMessage(), successfulOperation.getCode(), successfulOperation.isStatus()));
        userAccountDTO.setAccessToken(accessToken);
        userAccountDTO.setRole(userTypeService.getById(userAccount.getUserType()).getUserTypeName());

        return userAccountDTO;
    }

    @Override
    public ForgotPasswordEmailValidationResponse validateEmail(String email) throws GenericException {
        UserDTO userDTO = userService.getByEmail(email);
        String otpCode = OtpUtils.createOtpCode();
        final String EMAIL_BODY = MailUtils.getEmailBodyWhenReceivingOtp(userDTO.getFullName(), otpCode);
        final String EMAIL_OBJECT = Mail.VERIFY_EMAIL_SUBJECT;
        EmailDetail emailDetail = new EmailDetail(email, EMAIL_BODY, EMAIL_OBJECT);
        ForgotPasswordHistoryDTO forgotPasswordHistoryDTO = new ForgotPasswordHistoryDTO(userDTO.getUserId(), DateUtils.convertLocalDateTimeToTimeStamp(LocalDateTime.now().plusMinutes(Time.OTP_CODE_EXPIRE_AFTER_MINUTE)), otpCode, "", "");
        SuccessfulOperation response = SuccessfulOperation.FORGOT_PASSWORD_VALIDATE_EMAIL_SUCCESSFULLY;

        emailService.sendEmail(emailDetail);
        forgotPasswordHistoryService.create(forgotPasswordHistoryDTO);

        return new ForgotPasswordEmailValidationResponse(response.getMessage(), response.getCode(), response.isStatus(), userDTO.getUserId());
    }

    @Override
    public ForgotPasswordOtpValidationResponse validateOtp(String otpCode, Long userId) throws GenericException {
        ForgotPasswordHistoryDTO lastForgotPasswordRecord = forgotPasswordHistoryService.getLastByUserId(userId);

        if (!lastForgotPasswordRecord.getOtpCode().equals(otpCode) || lastForgotPasswordRecord.getResetAt() < DateUtils.convertLocalDateTimeToTimeStamp(LocalDateTime.now()))
            throw new GenericException(FailedOperation.FORGOT_PASSWORD_OTP_VALIDATION_FAILED);

        SuccessfulOperation response = SuccessfulOperation.FORGOT_PASSWORD_VALIDATE_OTP_SUCCESSFULLY;

        return new ForgotPasswordOtpValidationResponse(response.getMessage(), response.getCode(), response.isStatus(), BcryptUtils.hashText(otpCode + lastForgotPasswordRecord.getResetAt()), userId);
    }

    @Override
    public Response resetPassword(String newPassword, String resetPasswordToken, Long userId) throws GenericException {
        ForgotPasswordHistoryDTO lastForgotPasswordRecord = forgotPasswordHistoryService.getLastByUserId(userId);
        Long oldResetAt = lastForgotPasswordRecord.getResetAt();
        SuccessfulOperation response = SuccessfulOperation.FORGOT_PASSWORD_RESET_PASSWORD_SUCCESSFULLY;

        if (!BcryptUtils.isSameCode(lastForgotPasswordRecord.getOtpCode() + lastForgotPasswordRecord.getResetAt(), resetPasswordToken))
            throw new GenericException(FailedOperation.FORGOT_PASSWORD_INVALID_RESET_TOKEN);

        Optional<UserAccount> userAccountOptional = userAccountRepository.findByUserId(userId);

        if (userAccountOptional.isEmpty())
            throw new GenericException(FailedOperation.NOT_EXISTED_ACCOUNT);

        UserAccount userAccount = userAccountOptional.get();
        String newHashedPassword = BcryptUtils.hashText(newPassword);

        lastForgotPasswordRecord.setResetAt(DateUtils.convertLocalDateTimeToTimeStamp(LocalDateTime.now()));
        lastForgotPasswordRecord.setNewPassword(newHashedPassword);
        lastForgotPasswordRecord.setOldPassword(userAccount.getHashedPassword());
        forgotPasswordHistoryService.update(new ForgotPasswordId(userId, oldResetAt), lastForgotPasswordRecord);

        userAccount.setHashedPassword(newHashedPassword);
        userAccountRepository.save(userAccount);

        return new Response(response.getMessage(), response.getCode(), response.isStatus());
    }
}
