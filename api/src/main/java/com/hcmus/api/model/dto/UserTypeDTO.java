package com.hcmus.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserTypeDTO {
    private Long userTypeId;
    private String userTypeName;
}
