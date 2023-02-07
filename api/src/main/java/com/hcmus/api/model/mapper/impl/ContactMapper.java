package com.hcmus.api.model.mapper.impl;

import com.hcmus.api.model.dto.ContactDTO;
import com.hcmus.api.model.entity.Contact;
import com.hcmus.api.model.mapper.GenericMapper;
import org.springframework.stereotype.Component;

@Component(value = "contactMapper")
public class ContactMapper implements GenericMapper<Contact, ContactDTO> {
    @Override
    public Contact convertToEntity(ContactDTO object) {
        return null;
    }

    @Override
    public ContactDTO convertToDTO(Contact contact) {
        return new ContactDTO(contact.getContactId().getUserId(), contact.getContactId().getAccountNumber(), contact.getNickName(), "");
    }
}
