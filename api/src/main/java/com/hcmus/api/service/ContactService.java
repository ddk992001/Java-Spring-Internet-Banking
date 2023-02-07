package com.hcmus.api.service;

import com.hcmus.api.exception.GenericException;
import com.hcmus.api.model.dto.ContactDTO;

import java.util.List;

public interface ContactService {
    List<ContactDTO> getAllByUserId(Long userId) throws GenericException;
}
