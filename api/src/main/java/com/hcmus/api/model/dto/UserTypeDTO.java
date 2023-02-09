package com.hcmus.api.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserTypeDTO {
    @JsonProperty(value = "user_type_id")
    private Long userTypeId;

    @JsonProperty(value = "user_type_name")
    private String userTypeName;
}
