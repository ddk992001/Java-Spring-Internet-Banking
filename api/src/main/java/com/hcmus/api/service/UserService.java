package com.hcmus.api.service;

import com.hcmus.api.model.dto.UserDTO;

public interface UserService {
    UserDTO getByEmail(String email);
}
