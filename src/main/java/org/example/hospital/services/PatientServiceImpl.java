package org.example.hospital.services;
import lombok.RequiredArgsConstructor;
import org.example.hospital.converter.PatientConverter;
import org.example.hospital.dto.PatientDTO;
import org.example.hospital.entity.Patient;
import org.example.hospital.enums.Role;
import org.example.hospital.repositories.PatientRepository;
import org.example.hospital.services.PatientService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final PatientConverter patientConverter;
    @Override
    public PatientDTO createPatient(PatientDTO dto) {
        Patient patient = patientConverter.toEntity(dto);
        if (patient.getRole() == null) {
            patient.setRole(Role.PATIENT);
        }
        Patient saved = patientRepository.save(patient);
        return patientConverter.toDTO(saved);
    }
    @Override
    public PatientDTO getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));
        return patientConverter.toDTO(patient);
    }
    @Override
    public List<PatientDTO> getAllPatients() {
        return patientRepository.findAll()
                .stream()
                .map(patientConverter::toDTO)
                .collect(Collectors.toList());
    }
    @Override
    public PatientDTO updatePatient(Long id, PatientDTO dto) {
        Patient existing = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));
        existing.setName(dto.getName());
        existing.setSurname(dto.getSurname());
        existing.setEmail(dto.getEmail());
        existing.setPhoneNum(dto.getPhoneNum());
        existing.setDateOfBirth(dto.getDateOfBirth());
        existing.setGender(dto.getGender());
        existing.setAddress(dto.getAddress());
        existing.setIdCard(dto.getIdCard());
        existing.setMedicalInsurance(dto.getMedicalInsurance());
        if (dto.getBloodType() != null) {
            existing.setBloodType(existing.getBloodType());
        }
        Patient updated = patientRepository.save(existing);
        return patientConverter.toDTO(updated);
    }
    @Override
    public void deletePatient(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new RuntimeException("Patient not found with id: " + id);
        }
        patientRepository.deleteById(id);
    }
}
//