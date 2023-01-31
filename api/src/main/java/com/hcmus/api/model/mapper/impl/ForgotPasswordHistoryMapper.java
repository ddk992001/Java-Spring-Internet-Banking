package com.hcmus.api.model.mapper.impl;

import com.hcmus.api.model.dto.ForgotPasswordHistoryDTO;
import com.hcmus.api.model.entity.ForgotPasswordHistory;
import com.hcmus.api.model.entity.key.ForgotPasswordId;
import com.hcmus.api.model.mapper.GenericMapper;
import org.springframework.stereotype.Component;

@Component(value = "forgotPasswordHistoryMapper")
public class ForgotPasswordHistoryMapper implements GenericMapper<ForgotPasswordHistory, ForgotPasswordHistoryDTO> {
    @Override
    public ForgotPasswordHistory convertToEntity(ForgotPasswordHistoryDTO forgotPasswordHistoryDTO) {
        ForgotPasswordId forgotPasswordId = new ForgotPasswordId(forgotPasswordHistoryDTO.getUserId(), forgotPasswordHistoryDTO.getResetAt());
        return new ForgotPasswordHistory(forgotPasswordId, forgotPasswordHistoryDTO.getOtpCode(), forgotPasswordHistoryDTO.getOldPassword(), forgotPasswordHistoryDTO.getNewPassword());
    }

    @Override
    public ForgotPasswordHistoryDTO convertToDTO(ForgotPasswordHistory forgotPasswordHistory) {
        return new ForgotPasswordHistoryDTO(forgotPasswordHistory.getForgotPasswordId().getUserId(), forgotPasswordHistory.getForgotPasswordId().getResetAt(), forgotPasswordHistory.getOtpCode(), forgotPasswordHistory.getOldPassword(), forgotPasswordHistory.getNewPassword());
    }
}
