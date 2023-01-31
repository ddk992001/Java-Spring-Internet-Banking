package com.hcmus.api.service;

import com.hcmus.api.exception.GenericException;
import com.hcmus.api.model.dto.ForgotPasswordHistoryDTO;

public interface ForgotPasswordHistoryService {
    ForgotPasswordHistoryDTO getLastByUserId(Long userId) throws GenericException;
}
