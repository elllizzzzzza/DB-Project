package org.example.hospital.converter;
import org.example.hospital.dto.PatientDTO;
import org.example.hospital.entity.Patient;
import org.example.hospital.enums.BloodType;
import org.example.hospital.enums.Role;
import org.springframework.stereotype.Component;
@Component
public class PatientConverter {
    public PatientDTO toDTO(Patient patient) {
        if (patient == null) return null;
        PatientDTO dto = new PatientDTO();
        dto.setUserId(patient.getUserId());
        dto.setUsername(patient.getUsername());
        dto.setEmail(patient.getEmail());
        dto.setName(patient.getName());
        dto.setSurname(patient.getSurname());
        dto.setPhoneNum(patient.getPhoneNum());
        if (patient.getRole() != null) {
            dto.setRole(patient.getRole().name());
        }
        dto.setDateOfBirth(patient.getDateOfBirth());
        dto.setGender(patient.getGender());
        dto.setAddress(patient.getAddress());
        dto.setIdCard(patient.getIdCard());
        dto.setMedicalInsurance(patient.getMedicalInsurance());
        if (patient.getBloodType() != null) {
            dto.setBloodType(patient.getBloodType().name());
        }
        return dto;
    }
    public Patient toEntity(PatientDTO dto) {
        if (dto == null) return null;
        Patient patient = new Patient();
        patient.setUserId(dto.getUserId());
        patient.setUsername(dto.getUsername());
        patient.setEmail(dto.getEmail());
        patient.setName(dto.getName());
        patient.setSurname(dto.getSurname());
        patient.setPhoneNum(dto.getPhoneNum());
        if (dto.getRole() != null) {
            try {
                patient.setRole(Role.valueOf(dto.getRole()));
            } catch (IllegalArgumentException ex) {
            }
        }
        patient.setDateOfBirth(dto.getDateOfBirth());
        patient.setGender(dto.getGender());
        patient.setAddress(dto.getAddress());
        patient.setIdCard(dto.getIdCard());
        patient.setMedicalInsurance(dto.getMedicalInsurance());
        if (dto.getBloodType() != null) {
            try {
                patient.setBloodType(BloodType.valueOf(dto.getBloodType()));
            } catch (IllegalArgumentException ex) {
            }
        }
        return patient;
    }
}
