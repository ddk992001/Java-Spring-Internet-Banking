package com.hcmus.api.model.mapper.impl;

import com.hcmus.api.model.dto.UserDTO;
import com.hcmus.api.model.entity.User;
import com.hcmus.api.model.mapper.GenericMapper;
import org.springframework.stereotype.Component;

@Component(value = "userMapper")
public class UserMapper implements GenericMapper<User, UserDTO> {
    @Override
    public User convertToEntity(UserDTO object) {
        return null;
    }

    @Override
    public UserDTO convertToDTO(User user) {
        return new UserDTO(user.getUserId(), user.getFullName(), user.getEmail(), user.getPhone());
    }
}
