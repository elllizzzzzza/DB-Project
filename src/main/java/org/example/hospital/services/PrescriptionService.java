package org.example.hospital.services;
import org.example.hospital.dto.PrescriptionDTO;
import java.util.List;
public interface PrescriptionService {
    PrescriptionDTO createPrescription(PrescriptionDTO dto);
    PrescriptionDTO getPrescriptionById(Long id);
    List<PrescriptionDTO> getAllPrescriptions();
    PrescriptionDTO updatePrescription(Long id, PrescriptionDTO dto);
    void deletePrescription(Long id);
}
