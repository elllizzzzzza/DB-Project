package org.example.hospital.converter;

import org.example.hospital.dto.PatientDTO;
import org.example.hospital.entity.Patient;
import org.springframework.stereotype.Component;

@Component
public class PatientConverter implements Converter<Patient, PatientDTO> {

    @Override
    public PatientDTO convertToDTO(Patient entity, PatientDTO dto) {
        dto.setUserId(entity.getUserId());
        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getEmail());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setPhoneNum(entity.getPhoneNum());
        dto.setDateOfBirth(entity.getDateOfBirth());
        dto.setGender(entity.getGender());
        dto.setAddress(entity.getAddress());
        dto.setBloodType(entity.getBloodType());
        dto.setIdCard(entity.getIdCard());
        dto.setMedicalInsurance(entity.getMedicalInsurance());
        return dto;
    }

    @Override
    public Patient convertToEntity(PatientDTO dto, Patient entity) {
        entity.setUserId(dto.getUserId());
        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setPhoneNum(dto.getPhoneNum());
        entity.setDateOfBirth(dto.getDateOfBirth());
        entity.setGender(dto.getGender());
        entity.setAddress(dto.getAddress());
        entity.setBloodType(dto.getBloodType());
        entity.setIdCard(dto.getIdCard());
        entity.setMedicalInsurance(dto.getMedicalInsurance());
        return entity;
    }
}
