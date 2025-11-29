package org.example.hospital.converter;

import org.example.hospital.dto.LabTechnicianDTO;
import org.example.hospital.entity.Lab;
import org.example.hospital.entity.LabTechnician;
import org.springframework.stereotype.Component;

@Component
public class LabTechnicianConverter implements Converter<LabTechnician, LabTechnicianDTO> {

    @Override
    public LabTechnicianDTO convertToDTO(LabTechnician entity, LabTechnicianDTO dto) {
        dto.setUserId(entity.getUserId());
        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getEmail());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setPhoneNum(entity.getPhoneNum());
        dto.setEmploymentStartDate(entity.getEmploymentStartDate());
        if (entity.getLab() != null) dto.setLabId(entity.getLab().getLabId());
        return dto;
    }

    @Override
    public LabTechnician convertToEntity(LabTechnicianDTO dto, LabTechnician entity) {
        entity.setUserId(dto.getUserId());
        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setPhoneNum(dto.getPhoneNum());
        entity.setEmploymentStartDate(dto.getEmploymentStartDate());

        if (dto.getLabId() != null) {
            Lab lab = new Lab();
            lab.setLabId(dto.getLabId());
            entity.setLab(lab);
        }

        return entity;
    }
}
