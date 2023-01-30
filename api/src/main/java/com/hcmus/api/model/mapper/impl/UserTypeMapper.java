package com.hcmus.api.model.mapper.impl;

import com.hcmus.api.model.dto.UserTypeDTO;
import com.hcmus.api.model.entity.UserType;
import com.hcmus.api.model.mapper.GenericMapper;
import org.springframework.stereotype.Component;

@Component(value = "userTypeMapper")
public class UserTypeMapper implements GenericMapper<UserType, UserTypeDTO> {
    @Override
    public UserType convertToEntity(UserTypeDTO object) {
        return null;
    }

    @Override
    public UserTypeDTO convertToDTO(UserType userType) {
        return new UserTypeDTO(userType.getUserTypeId(), userType.getUserTypeName());
    }
}
