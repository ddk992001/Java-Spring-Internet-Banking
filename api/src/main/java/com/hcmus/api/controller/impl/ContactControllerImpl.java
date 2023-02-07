package com.hcmus.api.controller.impl;

import com.hcmus.api.common.form.UpdateContactForm;
import com.hcmus.api.common.response.Response;
import com.hcmus.api.controller.ContactController;
import com.hcmus.api.exception.GenericException;
import com.hcmus.api.model.dto.ContactDTO;
import com.hcmus.api.model.entity.key.ContactId;
import com.hcmus.api.service.impl.ContactServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        Response deleteContactResponse = contactService.deleteById(new ContactId(userId, accountNumber));
        return ResponseEntity.ok(deleteContactResponse);
    }

    @PutMapping("/{accountNumber}")
    @Override
    public ResponseEntity<Response> updateByContactId(@PathVariable Long userId, @PathVariable String accountNumber, @RequestBody UpdateContactForm updateContactForm) throws GenericException {
        ContactId contactId = new ContactId(userId, accountNumber);
        Response updateContactResponse = contactService.update(contactId, new ContactDTO(userId, accountNumber, updateContactForm.getNickName(), ""));
        return ResponseEntity.ok(updateContactResponse);
    }
}
