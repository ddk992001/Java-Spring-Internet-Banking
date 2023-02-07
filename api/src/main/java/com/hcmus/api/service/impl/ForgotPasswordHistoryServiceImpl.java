package com.hcmus.api.service.impl;

import com.hcmus.api.common.response.Response;
import com.hcmus.api.common.variables.ExceptionType;
import com.hcmus.api.common.variables.FailedOperation;
import com.hcmus.api.exception.GenericException;
import com.hcmus.api.model.dto.ForgotPasswordHistoryDTO;
import com.hcmus.api.model.entity.ForgotPasswordHistory;
import com.hcmus.api.model.entity.key.ForgotPasswordId;
import com.hcmus.api.model.mapper.impl.ForgotPasswordHistoryMapper;
import com.hcmus.api.repository.ForgotPasswordHistoryRepository;
import com.hcmus.api.service.ForgotPasswordHistoryService;
import com.hcmus.api.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service(value = "forgotPasswordHistoryService")
public class ForgotPasswordHistoryServiceImpl implements GenericService<ForgotPasswordHistoryDTO, ForgotPasswordId>, ForgotPasswordHistoryService {
    @Autowired
    private ForgotPasswordHistoryRepository forgotPasswordHistoryRepository;

    @Autowired
    @Qualifier(value = "forgotPasswordHistoryMapper")
    private ForgotPasswordHistoryMapper forgotPasswordHistoryMapper;

    @Override
    public List<ForgotPasswordHistoryDTO> getAll() {
        return null;
    }

    @Override
    public ForgotPasswordHistoryDTO getById(ForgotPasswordId id) throws GenericException {
        return null;
    }

    @Override
    public Response create(ForgotPasswordHistoryDTO forgotPasswordHistoryDTO) {
        forgotPasswordHistoryRepository.save(forgotPasswordHistoryMapper.convertToEntity(forgotPasswordHistoryDTO));
        return null;
    }

    @Override
    public Response update(ForgotPasswordId id, ForgotPasswordHistoryDTO forgotPasswordHistoryDTO) throws GenericException {
        Optional<ForgotPasswordHistory> forgotPasswordHistory = forgotPasswordHistoryRepository.findById(id);

        if (forgotPasswordHistory.isEmpty())
            throw new GenericException(FailedOperation.NOT_EXISTED_FORGOT_PASSWORD_HISTORY_RECORD, ExceptionType.COMMON_EXCEPTION);

        ForgotPasswordHistoryDTO oldForgotPasswordRecord = forgotPasswordHistoryMapper.convertToDTO(forgotPasswordHistory.get());

        oldForgotPasswordRecord.setOtpCode(forgotPasswordHistoryDTO.getOtpCode());
        oldForgotPasswordRecord.setOldPassword(forgotPasswordHistoryDTO.getOldPassword());
        oldForgotPasswordRecord.setNewPassword(forgotPasswordHistoryDTO.getNewPassword());
        oldForgotPasswordRecord.setResetAt(forgotPasswordHistoryDTO.getResetAt());

        forgotPasswordHistoryRepository.deleteById(id);
        forgotPasswordHistoryRepository.save(forgotPasswordHistoryMapper.convertToEntity(oldForgotPasswordRecord));

        return null;
    }

    @Override
    public Response deleteById(ForgotPasswordId id) {
        return null;
    }

    @Override
    public Response deleteAll() {
        return null;
    }

    @Override
    public ForgotPasswordHistoryDTO getLastByUserId(Long userId) throws GenericException {
        List<ForgotPasswordHistory> forgotPasswordHistory = forgotPasswordHistoryRepository.findByForgotPasswordIdUserId(userId);

        if (forgotPasswordHistory.size() == 0)
            throw new GenericException(FailedOperation.NOT_EXISTED_FORGOT_PASSWORD_HISTORY_RECORD, ExceptionType.COMMON_EXCEPTION);

        return forgotPasswordHistoryMapper.convertToDTO(forgotPasswordHistory.get(forgotPasswordHistory.size()-1));
    }
}
