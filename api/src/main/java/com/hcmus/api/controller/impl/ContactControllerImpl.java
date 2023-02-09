package com.hcmus.api.controller.impl;

import com.hcmus.api.common.form.UpdateContactForm;
import com.hcmus.api.common.response.Response;
import com.hcmus.api.common.variables.SuccessfulOperation;
import com.hcmus.api.controller.ContactController;
import com.hcmus.api.exception.GenericException;
import com.hcmus.api.model.dto.ContactDTO;
import com.hcmus.api.model.entity.key.ContactId;
import com.hcmus.api.service.impl.ContactServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/contacts")
public class ContactControllerImpl implements ContactController {
    @Autowired
    @Qualifier(value = "contactService")
    private ContactServiceImpl contactService;

    @GetMapping
    @Override
    public ResponseEntity<List<ContactDTO>> getAllByUserId(@PathVariable Long userId) throws GenericException {
        List<ContactDTO> contactDTOs = contactService.getAllByUserId(userId);
        return ResponseEntity.ok(contactDTOs);
    }

    @DeleteMapping("/{accountNumber}")
    @Override
    public ResponseEntity<Response> deleteByContactId(@PathVariable Long userId, @PathVariable String accountNumber) throws GenericException {
        SuccessfulOperation deleteContactResponse = SuccessfulOperation.DELETE_CONTACT_SUCCESSFULLY;
        contactService.deleteById(new ContactId(userId, accountNumber));
        return ResponseEntity.ok(new Response(deleteContactResponse.getMessage(), deleteContactResponse.getCode(), deleteContactResponse.isStatus()));
    }

    @PutMapping("/{accountNumber}")
    @Override
    public ResponseEntity<Response> updateByContactId(@PathVariable Long userId, @PathVariable String accountNumber, @RequestBody UpdateContactForm updateContactForm) throws GenericException {
        ContactId contactId = new ContactId(userId, accountNumber);
        SuccessfulOperation updateContactResponse = SuccessfulOperation.UPDATE_CONTACT_SUCCESSFULLY;

        contactService.update(contactId, new ContactDTO(userId, accountNumber, updateContactForm.getNickName(), ""));

        return ResponseEntity.ok(new Response(updateContactResponse.getMessage(), updateContactResponse.getCode(), updateContactResponse.isStatus()));
    }

    @PostMapping
    @Override
    public ResponseEntity<Response> addNewContact(@PathVariable Long userId, @RequestBody ContactDTO contactDTO) throws GenericException {
        SuccessfulOperation addContactResponse = SuccessfulOperation.ADD_NEW_CONTACT_SUCCESSFULLY;

        contactDTO.setUserId(userId);
        contactService.create(contactDTO);

        return ResponseEntity.status(201).body(new Response(addContactResponse.getMessage(), addContactResponse.getCode(), addContactResponse.isStatus()));

    }
}
