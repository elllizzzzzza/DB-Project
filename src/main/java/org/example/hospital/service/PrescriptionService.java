package org.example.hospital.service;

import org.example.hospital.converter.PrescriptionConverter;
import org.example.hospital.dto.PrescriptionDTO;
import org.example.hospital.entity.Appointment;
import org.example.hospital.entity.Drug;
import org.example.hospital.entity.Prescription;
import org.example.hospital.repository.AppointmentRepository;
import org.example.hospital.repository.DrugRepository;
import org.example.hospital.repository.PrescriptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PrescriptionService {

    private final PrescriptionRepository repo;
    private final AppointmentRepository appointmentRepo;
    private final DrugRepository drugRepo;
    private final PrescriptionConverter converter;

    public PrescriptionService(PrescriptionRepository repo, AppointmentRepository appointmentRepo,
                               DrugRepository drugRepo, PrescriptionConverter converter) {
        this.repo = repo;
        this.appointmentRepo = appointmentRepo;
        this.drugRepo = drugRepo;
        this.converter = converter;
    }

    public PrescriptionDTO createPrescription(PrescriptionDTO dto) {
        Prescription prescription = new Prescription();
        converter.convertToEntity(dto, prescription);

        Appointment appointment = appointmentRepo.findById(dto.getAppointmentId())
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        Drug drug = drugRepo.findById(dto.getDrugId())
                .orElseThrow(() -> new RuntimeException("Drug not found"));

        if (!drug.isAvailable()) throw new RuntimeException("Drug is out of stock");

        prescription.setAppointment(appointment);
        prescription.setDrug(drug);

        repo.save(prescription);
        return converter.convertToDTO(prescription, new PrescriptionDTO());
    }

    public List<PrescriptionDTO> getPrescriptionsForPatient(Long patientId) {
        return repo.findByAppointment_Patient_UserId(patientId).stream()
                .map(p -> converter.convertToDTO(p, new PrescriptionDTO()))
                .toList();
    }
}
