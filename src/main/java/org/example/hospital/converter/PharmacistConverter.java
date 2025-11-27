package org.example.hospital.converter;
import org.example.hospital.dto.PharmacistDTO;
import org.example.hospital.entity.Pharmacist;
import org.example.hospital.enums.Role;
import org.springframework.stereotype.Component;
@Component
public class PharmacistConverter {
    public PharmacistDTO toDTO(Pharmacist pharmacist) {
        if (pharmacist == null) return null;
        PharmacistDTO dto = new PharmacistDTO();
        dto.setUserId(pharmacist.getUserId());
        dto.setUsername(pharmacist.getUsername());
        dto.setEmail(pharmacist.getEmail());
        dto.setName(pharmacist.getName());
        dto.setSurname(pharmacist.getSurname());
        dto.setPhoneNum(pharmacist.getPhoneNum());
        if (pharmacist.getRole() != null) {
            dto.setRole(pharmacist.getRole().name());
        }
        return dto;
    }
    public Pharmacist toEntity(PharmacistDTO dto) {
        if (dto == null) return null;
        Pharmacist pharmacist = new Pharmacist();
        pharmacist.setUserId(dto.getUserId());
        pharmacist.setUsername(dto.getUsername());
        pharmacist.setEmail(dto.getEmail());
        pharmacist.setName(dto.getName());
        pharmacist.setSurname(dto.getSurname());
        pharmacist.setPhoneNum(dto.getPhoneNum());
        if (dto.getRole() != null) {
            try {
                pharmacist.setRole(Role.valueOf(dto.getRole()));
            } catch (IllegalArgumentException ex) {
            }
        }
        return pharmacist;
    }
}