package com.hcmus.api.repository;

import com.hcmus.api.model.entity.Contact;
import com.hcmus.api.model.entity.key.ContactId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, ContactId> {
    @Query
    List<Contact> findByContactIdUserId(Long userId);
}
