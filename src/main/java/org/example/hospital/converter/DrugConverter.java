package org.example.hospital.converter;

import org.example.hospital.dto.DrugDTO;
import org.example.hospital.entity.Drug;
import org.springframework.stereotype.Component;

@Component
public class DrugConverter implements Converter<Drug, DrugDTO> {

    @Override
    public DrugDTO convertToDTO(Drug entity, DrugDTO dto) {
        dto.setDrugId(entity.getDrugId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        dto.setAvailable(entity.isAvailable());
        return dto;
    }

    @Override
    public Drug convertToEntity(DrugDTO dto, Drug entity) {
        entity.setDrugId(dto.getDrugId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setAvailable(dto.isAvailable());
        return entity;
    }
}
