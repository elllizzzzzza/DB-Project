package org.example.hospital.services;
import lombok.RequiredArgsConstructor;
import org.example.hospital.converter.PrescriptionConverter;
import org.example.hospital.dto.PrescriptionDTO;
import org.example.hospital.entity.*;
import org.example.hospital.repositories.*;
import org.example.hospital.services.PrescriptionService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class PrescriptionServiceImpl implements PrescriptionService {
    private final PrescriptionRepository prescriptionRepository;
    private final DrugRepository drugRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final PharmacistRepository pharmacistRepository;
    private final ProcedureRepository procedureRepository;
    private final PrescriptionConverter prescriptionConverter;
    @Override
    public PrescriptionDTO createPrescription(PrescriptionDTO dto) {
        Drug drug = drugRepository.findById(dto.getDrugId())
                .orElseThrow(() -> new RuntimeException("Drug not found with id: " + dto.getDrugId()));
        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + dto.getPatientId()));
        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + dto.getDoctorId()));
        Pharmacist pharmacist = null;
        if (dto.getPharmacistId() != null) {
            pharmacist = pharmacistRepository.findById(dto.getPharmacistId())
                    .orElseThrow(() -> new RuntimeException("Pharmacist not found with id: " + dto.getPharmacistId()));
        }
        Procedure procedure = null;
        if (dto.getProcedureId() != null) {
            procedure = procedureRepository.findById(dto.getProcedureId())
                    .orElseThrow(() -> new RuntimeException("Procedure not found with id: " + dto.getProcedureId()));
        }
        Prescription prescription =
                prescriptionConverter.toEntity(dto, drug, patient, doctor, pharmacist, procedure);
        prescription.setTotalPrice(drug.getPrice());
        Prescription saved = prescriptionRepository.save(prescription);
        return prescriptionConverter.toDTO(saved);
    }
    @Override
    public PrescriptionDTO getPrescriptionById(Long id) {
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescription not found with id: " + id));
        return prescriptionConverter.toDTO(prescription);
    }
    @Override
    public List<PrescriptionDTO> getAllPrescriptions() {
        return prescriptionRepository.findAll()
                .stream()
                .map(prescriptionConverter::toDTO)
                .collect(Collectors.toList());
    }
    @Override
    public PrescriptionDTO updatePrescription(Long id, PrescriptionDTO dto) {
        Prescription existing = prescriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescription not found with id: " + id));
        existing.setDosage(dto.getDosage());
        existing.setFrequency(dto.getFrequency());
        if (dto.getDrugId() != null &&
                (existing.getDrug() == null ||
                        !existing.getDrug().getDrugId().equals(dto.getDrugId()))) {
            Drug drug = drugRepository.findById(dto.getDrugId())
                    .orElseThrow(() -> new RuntimeException("Drug not found with id: " + dto.getDrugId()));
            existing.setDrug(drug);
            existing.setTotalPrice(drug.getPrice());
        }
        if (dto.getPatientId() != null &&
                (existing.getPatient() == null ||
                        !existing.getPatient().getUserId().equals(dto.getPatientId()))) {
            Patient patient = patientRepository.findById(dto.getPatientId())
                    .orElseThrow(() -> new RuntimeException("Patient not found with id: " + dto.getPatientId()));
            existing.setPatient(patient);
        }
        if (dto.getDoctorId() != null &&
                (existing.getDoctor() == null ||
                        !existing.getDoctor().getUserId().equals(dto.getDoctorId()))) {
            Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                    .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + dto.getDoctorId()));
            existing.setDoctor(doctor);
        }
        if (dto.getPharmacistId() != null) {
            Pharmacist pharmacist = pharmacistRepository.findById(dto.getPharmacistId())
                    .orElseThrow(() -> new RuntimeException("Pharmacist not found with id: " + dto.getPharmacistId()));
            existing.setDispensedBy(pharmacist);
        }
        if (dto.getProcedureId() != null) {
            Procedure procedure = procedureRepository.findById(dto.getProcedureId())
                    .orElseThrow(() -> new RuntimeException("Procedure not found with id: " + dto.getProcedureId()));
            existing.setProcedure(procedure);
        }
        Prescription updated = prescriptionRepository.save(existing);
        return prescriptionConverter.toDTO(updated);
    }
    @Override
    public void deletePrescription(Long id) {
        if (!prescriptionRepository.existsById(id)) {
            throw new RuntimeException("Prescription not found with id: " + id);
        }
        prescriptionRepository.deleteById(id);
    }
}
