package com.hcmus.api.service.impl;

import com.hcmus.api.common.utils.JwtUtils;
import com.hcmus.api.common.variables.Bank;
import com.hcmus.api.common.variables.ExceptionType;
import com.hcmus.api.common.variables.FailedOperation;
import com.hcmus.api.exception.GenericException;
import com.hcmus.api.model.dto.BankDTO;
import com.hcmus.api.model.dto.BankingAccountDTO;
import com.hcmus.api.model.dto.ContactDTO;
import com.hcmus.api.model.dto.UserDTO;
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

    @Autowired
    @Qualifier(value = "userService")
    private UserServiceImpl userService;

    @Autowired
    @Qualifier(value = "bankService")
    private BankServiceImpl bankService;

    @Override
    public List<ContactDTO> getAll() {
        return null;
    }

    @Override
    public ContactDTO getById(ContactId id) throws GenericException {
        return null;
    }

    @Override
    public void create(ContactDTO contactDTO) throws GenericException {
        ContactId contactId = new ContactId(contactDTO.getUserId(), contactDTO.getAccountNumber());
        boolean isExistedContact = contactRepository.existsById(contactId);

        if (isExistedContact)
            throw new GenericException(FailedOperation.EXISTED_CONTACT, ExceptionType.COMMON_EXCEPTION);

        if (contactDTO.getBankCode().equals(Bank.DEFAULT_BANK)) {
            BankingAccountDTO bankingAccount = bankingAccountService.getById(contactDTO.getAccountNumber());

            if (bankingAccount.getUserId().equals(contactDTO.getUserId()))
                throw new GenericException(FailedOperation.ADD_OWN_ACCOUNT_TO_CONTACT, ExceptionType.COMMON_EXCEPTION);

            if (!bankingAccount.isSpendAccount())
                throw new GenericException(FailedOperation.ADD_SAVING_ACCOUNT_TO_CONTACT, ExceptionType.COMMON_EXCEPTION);

            if (contactDTO.getNickName() == null) {
                UserDTO userContact = userService.getById(bankingAccount.getUserId());
                contactDTO.setNickName(userContact.getFullName());
            }

            contactRepository.save(new Contact(contactId, contactDTO.getNickName()));
        }
        else {
            // add external contact
            BankDTO connectedBank = bankService.getById(contactDTO.getBankCode());
        }
    }

    @Override
    public void update(ContactId contactId, ContactDTO contactDTO) throws GenericException {
        Optional<Contact> contactOptional = contactRepository.findById(contactId);

        if (contactOptional.isEmpty())
            throw new GenericException(FailedOperation.NOT_EXISTED_CONTACT, ExceptionType.COMMON_EXCEPTION);

        Contact contact = contactOptional.get();
        contact.setNickName(contactDTO.getNickName());
        contactRepository.save(contact);
    }

    @Override
    public void deleteById(ContactId contactId) throws GenericException {
        try {
            contactRepository.deleteById(contactId);
        } catch (EmptyResultDataAccessException exception) {
            throw new GenericException(FailedOperation.NOT_EXISTED_CONTACT, ExceptionType.COMMON_EXCEPTION);
        }
    }

    @Override
    public void deleteAll() {

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
