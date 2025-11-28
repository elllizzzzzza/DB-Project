package org.example.hospital.services;
import lombok.RequiredArgsConstructor;
import org.example.hospital.converter.ProcedureConverter;
import org.example.hospital.dto.ProcedureDTO;
import org.example.hospital.entity.*;
import org.example.hospital.repositories.*;
import org.example.hospital.services.ProcedureService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class ProcedureServiceImpl implements ProcedureService {
    private final ProcedureRepository procedureRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final LabRepository labRepository;
    private final LabTechnicianRepository labTechnicianRepository;
    private final ProcedureConverter procedureConverter;
    @Override
    public ProcedureDTO createProcedure(ProcedureDTO dto) {
        Patient patient = null;
        if (dto.getPatientId() != null) {
            patient = patientRepository.findById(dto.getPatientId())
                    .orElseThrow(() -> new RuntimeException("Patient not found with id: " + dto.getPatientId()));
        }
        Doctor doctor = null;
        if (dto.getDoctorId() != null) {
            doctor = doctorRepository.findById(dto.getDoctorId())
                    .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + dto.getDoctorId()));
        }
        Lab lab = null;
        if (dto.getLabId() != null) {
            lab = labRepository.findById(dto.getLabId())
                    .orElseThrow(() -> new RuntimeException("Lab not found with id: " + dto.getLabId()));
        }
        LabTechnician labTechnician = null;
        if (dto.getLabTechnicianId() != null) {
            labTechnician = labTechnicianRepository.findById(dto.getLabTechnicianId())
                    .orElseThrow(() -> new RuntimeException("Lab technician not found with id: " + dto.getLabTechnicianId()));
        }
        Procedure procedure = procedureConverter.toEntity(dto, patient, lab, labTechnician, doctor);
        Procedure saved = procedureRepository.save(procedure);
        return procedureConverter.toDTO(saved);
    }
    @Override
    public ProcedureDTO getProcedureById(Long id) {
        Procedure procedure = procedureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Procedure not found with id: " + id));
        return procedureConverter.toDTO(procedure);
    }
    @Override
    public List<ProcedureDTO> getAllProcedures() {
        return procedureRepository.findAll()
                .stream()
                .map(procedureConverter::toDTO)
                .collect(Collectors.toList());
    }
    @Override
    public ProcedureDTO updateProcedure(Long id, ProcedureDTO dto) {
        Procedure existing = procedureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Procedure not found with id: " + id));
        existing.setTestName(dto.getTestName());
        existing.setDate(String.valueOf(dto.getDate()));
        existing.setResult(dto.getResult());
        if (dto.getPatientId() != null) {
            Patient patient = patientRepository.findById(dto.getPatientId())
                    .orElseThrow(() -> new RuntimeException("Patient not found with id: " + dto.getPatientId()));
            existing.setPatient(patient);
        }
        if (dto.getDoctorId() != null) {
            Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                    .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + dto.getDoctorId()));
            existing.setDoctor(doctor);
        }
        if (dto.getLabId() != null) {
            Lab lab = labRepository.findById(dto.getLabId())
                    .orElseThrow(() -> new RuntimeException("Lab not found with id: " + dto.getLabId()));
            existing.setLab(lab);
        }
        if (dto.getLabTechnicianId() != null) {
            LabTechnician labTechnician = labTechnicianRepository.findById(dto.getLabTechnicianId())
                    .orElseThrow(() -> new RuntimeException("Lab technician not found with id: " + dto.getLabTechnicianId()));
            existing.setLabTechnician(labTechnician);
        }
        Procedure updated = procedureRepository.save(existing);
        return procedureConverter.toDTO(updated);
    }
    @Override
    public void deleteProcedure(Long id) {
        if (!procedureRepository.existsById(id)) {
            throw new RuntimeException("Procedure not found with id: " + id);
        }
        procedureRepository.deleteById(id);
    }
}
