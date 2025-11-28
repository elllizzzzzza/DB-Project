package org.example.hospital.services;
import org.example.hospital.dto.PharmacistDTO;
import java.util.List;
public interface PharmacistService {
    PharmacistDTO createPharmacist(PharmacistDTO dto);
    PharmacistDTO getPharmacistById(Long id);
    List<PharmacistDTO> getAllPharmacists();
    PharmacistDTO updatePharmacist(Long id, PharmacistDTO dto);
    void deletePharmacist(Long id);
}
