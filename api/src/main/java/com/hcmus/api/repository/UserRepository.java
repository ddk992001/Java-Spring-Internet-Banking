package com.hcmus.api.repository;

import com.hcmus.api.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query
    Optional<User> findByEmail(String email);
}
