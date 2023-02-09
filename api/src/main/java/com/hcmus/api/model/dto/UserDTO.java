package com.hcmus.api.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    @JsonProperty(value = "user_id")
    private Long userId;

    @JsonProperty(value = "full_name")
    private String fullName;

    private String email;
    private String phone;
}
