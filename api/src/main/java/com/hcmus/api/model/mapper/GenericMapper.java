package com.hcmus.api.model.mapper;

public interface GenericMapper<T, DTO> {
    T convertToEntity(DTO object);
    DTO convertToDTO(T object);
}
