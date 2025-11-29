package org.example.hospital.converter;

import org.example.hospital.dto.LabDTO;
import org.example.hospital.entity.Lab;
import org.springframework.stereotype.Component;

@Component
public class LabConverter implements Converter<Lab, LabDTO> {

    @Override
    public LabDTO convertToDTO(Lab entity, LabDTO dto) {
        dto.setLabId(entity.getLabId());
        dto.setName(entity.getName());
        dto.setLocation(entity.getLocation());
        dto.setWorkingHours(entity.getWorkingHours());
        return dto;
    }

    @Override
    public Lab convertToEntity(LabDTO dto, Lab entity) {
        entity.setLabId(dto.getLabId());
        entity.setName(dto.getName());
        entity.setLocation(dto.getLocation());
        entity.setWorkingHours(dto.getWorkingHours());
        return entity;
    }
}
