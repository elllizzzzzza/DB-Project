package org.example.hospital.converter;

import org.example.hospital.dto.DepartmentDTO;
import org.example.hospital.entity.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentConverter implements Converter<Department, DepartmentDTO> {

    @Override
    public DepartmentDTO convertToDTO(Department entity, DepartmentDTO dto) {
        dto.setDeptId(entity.getDeptId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    @Override
    public Department convertToEntity(DepartmentDTO dto, Department entity) {
        entity.setDeptId(dto.getDeptId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        return entity;
    }
}
