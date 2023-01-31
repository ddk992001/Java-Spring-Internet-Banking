package com.hcmus.api.service.impl;

import com.hcmus.api.common.variables.FailedOperation;
import com.hcmus.api.exception.GenericException;
import com.hcmus.api.model.dto.UserDTO;
import com.hcmus.api.model.entity.User;
import com.hcmus.api.model.mapper.impl.UserMapper;
import com.hcmus.api.repository.UserRepository;
import com.hcmus.api.service.GenericService;
import com.hcmus.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service(value = "userService")
public class UserServiceImpl implements GenericService<UserDTO, Long>, UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Qualifier(value = "userMapper")
    private UserMapper userMapper;

    @Override
    public List<UserDTO> getAll() {
        return null;
    }

    @Override
    public UserDTO getById(Long id) {
        return null;
    }

    @Override
    public void create(UserDTO object) {

    }

    @Override
    public void update(Long id, UserDTO object) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public UserDTO getByEmail(String email) throws GenericException {
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty())
            throw new GenericException(FailedOperation.NOT_EXISTED_EMAIL);

        return userMapper.convertToDTO(user.get());
    }
}
