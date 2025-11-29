package org.example.hospital.converter;

import org.example.hospital.dto.AdminDTO;
import org.example.hospital.entity.Admin;
import org.springframework.stereotype.Component;

@Component
public class AdminConverter implements Converter<Admin, AdminDTO> {

    @Override
    public AdminDTO convertToDTO(Admin entity, AdminDTO dto) {
        dto.setUserId(entity.getUserId());
        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getEmail());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setPhoneNum(entity.getPhoneNum());
        return dto;
    }

    @Override
    public Admin convertToEntity(AdminDTO dto, Admin entity) {
        entity.setUserId(dto.getUserId());
        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setPhoneNum(dto.getPhoneNum());
        return entity;
    }
}
