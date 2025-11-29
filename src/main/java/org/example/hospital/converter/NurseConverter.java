package org.example.hospital.converter;

import org.example.hospital.dto.NurseDTO;
import org.example.hospital.entity.Department;
import org.example.hospital.entity.Nurse;
import org.springframework.stereotype.Component;

@Component
public class NurseConverter implements Converter<Nurse, NurseDTO> {

    @Override
    public NurseDTO convertToDTO(Nurse entity, NurseDTO dto) {
        dto.setUserId(entity.getUserId());
        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getEmail());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setPhoneNum(entity.getPhoneNum());
        dto.setNurseLevel(entity.getNurseLevel());
        if (entity.getDepartment() != null) dto.setDepartmentId(entity.getDepartment().getDeptId());
        return dto;
    }

    @Override
    public Nurse convertToEntity(NurseDTO dto, Nurse entity) {
        entity.setUserId(dto.getUserId());
        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setPhoneNum(dto.getPhoneNum());
        entity.setNurseLevel(dto.getNurseLevel());

        if (dto.getDepartmentId() != null) {
            Department department = new Department();
            department.setDeptId(dto.getDepartmentId());
            entity.setDepartment(department);
        }

        return entity;
    }
}
