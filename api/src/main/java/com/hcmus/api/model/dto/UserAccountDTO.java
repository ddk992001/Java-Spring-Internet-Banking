package com.hcmus.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserAccountDTO {
    private String username;
    private Long userId;
    private String accessToken;
    private String refreshToken;
    private String role;
}
