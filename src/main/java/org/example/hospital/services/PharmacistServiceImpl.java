package org.example.hospital.services;
import lombok.RequiredArgsConstructor;
import org.example.hospital.converter.PharmacistConverter;
import org.example.hospital.dto.PharmacistDTO;
import org.example.hospital.entity.Pharmacist;
import org.example.hospital.enums.Role;
import org.example.hospital.repositories.PharmacistRepository;
import org.example.hospital.services.PharmacistService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class PharmacistServiceImpl implements PharmacistService {
    private final PharmacistRepository pharmacistRepository;
    private final PharmacistConverter pharmacistConverter;
    @Override
    public PharmacistDTO createPharmacist(PharmacistDTO dto) {
        Pharmacist pharmacist = pharmacistConverter.toEntity(dto);
        if (pharmacist.getRole() == null) {
            pharmacist.setRole(Role.PHARMACIST); // adjust to your enum value
        }
        Pharmacist saved = pharmacistRepository.save(pharmacist);
        return pharmacistConverter.toDTO(saved);
    }
    @Override
    public PharmacistDTO getPharmacistById(Long id) {
        Pharmacist pharmacist = pharmacistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pharmacist not found with id: " + id));
        return pharmacistConverter.toDTO(pharmacist);
    }
    @Override
    public List<PharmacistDTO> getAllPharmacists() {
        return pharmacistRepository.findAll()
                .stream()
                .map(pharmacistConverter::toDTO)
                .collect(Collectors.toList());
    }
    @Override
    public PharmacistDTO updatePharmacist(Long id, PharmacistDTO dto) {
        Pharmacist existing = pharmacistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pharmacist not found with id: " + id));
        existing.setName(dto.getName());
        existing.setSurname(dto.getSurname());
        existing.setEmail(dto.getEmail());
        existing.setPhoneNum(dto.getPhoneNum());
        if (dto.getRole() != null) {
            try {
                existing.setRole(Role.valueOf(dto.getRole()));
            } catch (IllegalArgumentException ex) {
            }
        }
        Pharmacist updated = pharmacistRepository.save(existing);
        return pharmacistConverter.toDTO(updated);
    }
    @Override
    public void deletePharmacist(Long id) {
        if (!pharmacistRepository.existsById(id)) {
            throw new RuntimeException("Pharmacist not found with id: " + id);
        }
        pharmacistRepository.deleteById(id);
    }
}
