package com.hcmus.api.model.entity;

import com.hcmus.api.model.entity.key.ContactId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "contact")
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
    @EmbeddedId
    private ContactId contactId;

    @Column(name = "nick_name")
    private String nickName;
}
