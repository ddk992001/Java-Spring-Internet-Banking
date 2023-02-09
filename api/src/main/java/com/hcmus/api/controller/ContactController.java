package com.hcmus.api.controller;

import com.hcmus.api.common.form.UpdateContactForm;
import com.hcmus.api.common.response.Response;
import com.hcmus.api.model.dto.ContactDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ContactController {
    ResponseEntity<List<ContactDTO>> getAllByUserId(Long userId);
    ResponseEntity<Response> deleteByContactId(Long userId, String accountNumber);
    ResponseEntity<Response> updateByContactId(Long userId, String accountNumber, UpdateContactForm updateContactForm);
    ResponseEntity<Response> addNewContact(Long userId, ContactDTO contactDTO);
}
