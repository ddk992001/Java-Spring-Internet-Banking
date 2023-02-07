package com.hcmus.api.service.impl;

import com.hcmus.api.common.response.Response;
import com.hcmus.api.common.variables.ExceptionType;
import com.hcmus.api.common.variables.FailedOperation;
import com.hcmus.api.common.variables.SuccessfulOperation;
import com.hcmus.api.exception.GenericException;
import com.hcmus.api.model.dto.BankingAccountDTO;
import com.hcmus.api.model.dto.ContactDTO;
import com.hcmus.api.model.entity.Contact;
import com.hcmus.api.model.entity.key.ContactId;
import com.hcmus.api.model.mapper.impl.ContactMapper;
import com.hcmus.api.repository.ContactRepository;
import com.hcmus.api.service.ContactService;
import com.hcmus.api.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("contactService")
public class ContactServiceImpl implements GenericService<ContactDTO, ContactId>, ContactService {
    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    @Qualifier(value = "contactMapper")
    private ContactMapper contactMapper;

    @Autowired
    @Qualifier(value = "bankingAccountService")
    private BankingAccountServiceImpl bankingAccountService;

    @Override
    public List<ContactDTO> getAll() {
        return null;
    }

    @Override
    public ContactDTO getById(ContactId id) throws GenericException {
        return null;
    }

    @Override
    public Response create(ContactDTO object) {
        return null;
    }

    @Override
    public Response update(ContactId contactId, ContactDTO contactDTO) throws GenericException {
        SuccessfulOperation response = SuccessfulOperation.UPDATE_CONTACT_SUCCESSFULLY;
        Optional<Contact> contactOptional = contactRepository.findById(contactId);

        if (contactOptional.isEmpty())
            throw new GenericException(FailedOperation.NOT_EXISTED_CONTACT, ExceptionType.COMMON_EXCEPTION);

        Contact contact = contactOptional.get();
        contact.setNickName(contactDTO.getNickName());
        contactRepository.save(contact);

        return new Response(response.getMessage(), response.getCode(), response.isStatus());
    }

    @Override
    public Response deleteById(ContactId contactId) throws GenericException {
        SuccessfulOperation response = SuccessfulOperation.DELETE_CONTACT_SUCCESSFULLY;

        try {
            contactRepository.deleteById(contactId);
        } catch (EmptyResultDataAccessException exception) {
            throw new GenericException(FailedOperation.NOT_EXISTED_CONTACT, ExceptionType.COMMON_EXCEPTION);
        }

        return new Response(response.getMessage(), response.getCode(), response.isStatus());
    }

    @Override
    public Response deleteAll() {
        return null;
    }

    @Override
    public List<ContactDTO> getAllByUserId(Long userId) throws GenericException {
        List<Contact> contacts = contactRepository.findByContactIdUserId(userId);
        List<ContactDTO> contactDTOs = contacts
                .stream()
                .map(contact -> contactMapper.convertToDTO(contact))
                .toList();

        contactDTOs.forEach(contactDTO -> {
            BankingAccountDTO bankingAccountDTO = bankingAccountService.getById(contactDTO.getAccountNumber());
            contactDTO.setBankCode(bankingAccountDTO.getBankCode());
        });

        return contactDTOs;
    }
}
