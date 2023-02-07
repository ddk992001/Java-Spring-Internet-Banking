package com.hcmus.api.service.impl;

import com.hcmus.api.common.response.Response;
import com.hcmus.api.common.variables.ExceptionType;
import com.hcmus.api.common.variables.FailedOperation;
import com.hcmus.api.exception.GenericException;
import com.hcmus.api.model.dto.UserTypeDTO;
import com.hcmus.api.model.entity.UserType;
import com.hcmus.api.model.mapper.impl.UserTypeMapper;
import com.hcmus.api.repository.UserTypeRepository;
import com.hcmus.api.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service(value = "userTypeService")
public class UserTypeServiceImpl implements GenericService<UserTypeDTO, Long> {
    @Autowired
    private UserTypeRepository userTypeRepository;

    @Autowired
    @Qualifier(value = "userTypeMapper")
    private UserTypeMapper userTypeMapper;

    @Override
    public List<UserTypeDTO> getAll() {
        return null;
    }

    @Override
    public UserTypeDTO getById(Long userTypeId) throws GenericException {
        Optional<UserType> userType = userTypeRepository.findById(userTypeId);

        if (userType.isEmpty())
            throw new GenericException(FailedOperation.NOT_EXISTED_USER_TYPE, ExceptionType.COMMON_EXCEPTION);

        return userTypeMapper.convertToDTO(userType.get());
    }

    @Override
    public Response create(UserTypeDTO object) {
        return null;
    }

    @Override
    public Response update(Long id, UserTypeDTO object) {
        return null;
    }

    @Override
    public Response deleteById(Long id) {
        return null;
    }

    @Override
    public Response deleteAll() {
        return null;
    }
}
