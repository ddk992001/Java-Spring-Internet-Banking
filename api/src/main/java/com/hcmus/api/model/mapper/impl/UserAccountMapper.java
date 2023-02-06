package com.hcmus.api.model.mapper.impl;

import com.hcmus.api.model.dto.UserAccountDTO;
import com.hcmus.api.model.entity.UserAccount;
import com.hcmus.api.model.mapper.GenericMapper;
import org.springframework.stereotype.Component;

@Component(value = "userAccountMapper")
public class UserAccountMapper implements GenericMapper<UserAccount, UserAccountDTO> {
    @Override
    public UserAccount convertToEntity(UserAccountDTO object) {
        return null;
    }

    @Override
    public UserAccountDTO convertToDTO(UserAccount userAccount) {
        return new UserAccountDTO(userAccount.getUsername(), userAccount.getUserId(), "", userAccount.getRefreshToken(), "");
    }
}
