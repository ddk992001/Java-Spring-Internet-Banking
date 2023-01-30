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
@Table(name = "user_account")
public class UserAccount {
    @Column(name = "user_id", unique = true)
    private Long userId;

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String hashedPassword;

    @Column(name = "user_type_id")
    private Long userType;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "last_expired_at")
    private Long lastExpiredAt;
}
