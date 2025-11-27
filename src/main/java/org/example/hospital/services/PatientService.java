package org.example.hospital.services;

import org.example.hospital.dto.PatientDTO;

import java.util.List;

public interface PatientService {

    PatientDTO createPatient(PatientDTO dto);

    PatientDTO getPatientById(Long id);

    List<PatientDTO> getAllPatients();

    PatientDTO updatePatient(Long id, PatientDTO dto);

    void deletePatient(Long id);
}
