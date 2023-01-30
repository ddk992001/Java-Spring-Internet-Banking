package com.hcmus.api.model.dto;

import com.hcmus.api.common.Response;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserAccountDTO {
    private Response responseInfo;
    private String username;
    private Long userId;
    private String accessToken;
    private String refreshToken;
    private String role;
}
