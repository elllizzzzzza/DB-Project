package org.example.hospital.converter;
import org.example.hospital.dto.DoctorDTO;
import org.example.hospital.entity.Department;
import org.example.hospital.entity.Doctor;
import org.example.hospital.enums.Role;
import org.springframework.stereotype.Component;
@Component
public class DoctorConverter {
    public DoctorDTO toDTO(Doctor doctor) {
        if (doctor == null) return null;
        DoctorDTO dto = new DoctorDTO();
        dto.setUserId(doctor.getUserId());
        dto.setUsername(doctor.getUsername());
        dto.setEmail(doctor.getEmail());
        dto.setName(doctor.getName());
        dto.setSurname(doctor.getSurname());
        dto.setPhoneNum(doctor.getPhoneNum());
        if (doctor.getRole() != null) {
            dto.setRole(doctor.getRole().name());
        }
        dto.setInfo(doctor.getInfo());
        dto.setLicenseNumber(doctor.getLicenseNum());
        dto.setRoomNumber(doctor.getRoomNum());
        if (doctor.getDepartment() != null) {
            dto.setDepartmentId(doctor.getDepartment().getDeptId());
        }
        return dto;
    }
    public Doctor toEntity(DoctorDTO dto, Department department) {
        if (dto == null) return null;
        Doctor doctor = new Doctor();
        doctor.setUserId(dto.getUserId());
        doctor.setUsername(dto.getUsername());
        doctor.setEmail(dto.getEmail());
        doctor.setName(dto.getName());
        doctor.setSurname(dto.getSurname());
        doctor.setPhoneNum(dto.getPhoneNum());
        if (dto.getRole() != null) {
            try {
                doctor.setRole(Role.valueOf(dto.getRole()));
            } catch (IllegalArgumentException ex) {
            }
        }
        doctor.setInfo(dto.getInfo());
        doctor.setLicenseNum(dto.getLicenseNumber());
        doctor.setRoomNum(dto.getRoomNumber());
        doctor.setDepartment(department);
        return doctor;
    }
}
