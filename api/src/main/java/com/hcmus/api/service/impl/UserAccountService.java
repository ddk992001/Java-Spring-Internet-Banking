package com.hcmus.api.service.impl;

import com.hcmus.api.common.DateUtils;
import com.hcmus.api.common.FailedOperation;
import com.hcmus.api.common.JwtUtils;
import com.hcmus.api.common.PasswordUtils;
import com.hcmus.api.common.Response;
import com.hcmus.api.common.SuccessfulOperation;
import com.hcmus.api.exception.GenericException;
import com.hcmus.api.model.dto.UserAccountDTO;
import com.hcmus.api.model.entity.UserAccount;
import com.hcmus.api.model.mapper.impl.UserAccountMapper;
import com.hcmus.api.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service(value = "userAccountService")
public class UserAccountService {
    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    @Qualifier(value = "userAccountMapper")
    private UserAccountMapper userAccountMapper;

    @Autowired
    @Qualifier(value = "userTypeService")
    private UserTypeService userTypeService;

    public UserAccountDTO authenticateAccount(String username, String password) throws GenericException {
        Optional<UserAccount> userAccountOptional = userAccountRepository.findById(username);

        if (userAccountOptional.isEmpty() || !PasswordUtils.isSamePassword(password, userAccountOptional.get().getHashedPassword()))
            throw new GenericException(FailedOperation.LOGIN_FAILED);

        UserAccount userAccount = userAccountOptional.get();
        SuccessfulOperation successfulOperation = SuccessfulOperation.LOGIN_SUCCESSFULLY;
        String accessToken = JwtUtils.createAccessToken(username);
        UserAccountDTO userAccountDTO = userAccountMapper.convertToDTO(userAccount);

        userAccount.setLastExpiredAt(DateUtils.convertLocalDateTimeToTimeStamp(LocalDateTime.now().plusHours(1)));
        userAccountRepository.save(userAccount);

        userAccountDTO.setResponseInfo(new Response(successfulOperation.getMessage(), successfulOperation.getCode(), successfulOperation.isStatus()));
        userAccountDTO.setAccessToken(accessToken);
        userAccountDTO.setRole(userTypeService.getById(userAccount.getUserType()).getUserTypeName());

        return userAccountDTO;
    }
}
