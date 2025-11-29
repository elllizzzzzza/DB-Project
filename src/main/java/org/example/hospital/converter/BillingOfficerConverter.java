package org.example.hospital.converter;

import org.example.hospital.dto.BillingOfficerDTO;
import org.example.hospital.entity.BillingOfficer;
import org.springframework.stereotype.Component;

@Component
public class BillingOfficerConverter implements Converter<BillingOfficer, BillingOfficerDTO> {

    @Override
    public BillingOfficerDTO convertToDTO(BillingOfficer entity, BillingOfficerDTO dto) {
        dto.setUserId(entity.getUserId());
        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getEmail());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setPhoneNum(entity.getPhoneNum());
        dto.setRoomNum(entity.getRoomNum());
        return dto;
    }

    @Override
    public BillingOfficer convertToEntity(BillingOfficerDTO dto, BillingOfficer entity) {
        entity.setUserId(dto.getUserId());
        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setPhoneNum(dto.getPhoneNum());
        entity.setRoomNum(dto.getRoomNum());
        return entity;
    }
}
