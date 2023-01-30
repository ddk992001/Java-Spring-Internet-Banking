package com.hcmus.api.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "user_type")
public class UserType {
    @Id
    @Column(name = "user_type_id")
    private Long userTypeId;

    @Column(name = "user_type_name")
    private String userTypeName;
}
