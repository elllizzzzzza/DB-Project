package org.example.hospital.services;
import org.example.hospital.dto.DoctorDTO;
import java.util.List;
public interface DoctorService {
    DoctorDTO createDoctor(DoctorDTO dto);
    DoctorDTO getDoctorById(Long id);
    List<DoctorDTO> getAllDoctors();
    DoctorDTO updateDoctor(Long id, DoctorDTO dto);
    void deleteDoctor(Long id);
}
