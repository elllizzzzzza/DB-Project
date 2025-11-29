package org.example.hospital.service;

import org.example.hospital.dto.AppointmentDTO;
import org.example.hospital.dto.BillDTO;
import org.example.hospital.dto.DoctorDTO;
import org.example.hospital.dto.DrugDTO;
import org.example.hospital.entity.Appointment;
import org.example.hospital.entity.Bill;
import org.example.hospital.entity.Doctor;
import org.example.hospital.entity.Drug;
import org.example.hospital.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportingService {

    private final AppointmentRepository appointmentRepo;
    private final DoctorRepository doctorRepo;
    private final DrugRepository drugRepo;
    private final PrescriptionRepository prescriptionRepo;
    private final BillRepository billRepo;

    public ReportingService(AppointmentRepository appointmentRepo, DoctorRepository doctorRepo,
                            DrugRepository drugRepo, PrescriptionRepository prescriptionRepo,
                            BillRepository billRepo) {
        this.appointmentRepo = appointmentRepo;
        this.doctorRepo = doctorRepo;
        this.drugRepo = drugRepo;
        this.prescriptionRepo = prescriptionRepo;
        this.billRepo = billRepo;
    }

    // Example: appointments for a given period
    public List<Appointment> getAppointments(LocalDate start, LocalDate end) {
        return appointmentRepo.findAll().stream()
                .filter(a -> !a.getStartTime().toLocalDate().isBefore(start) &&
                        !a.getEndTime().toLocalDate().isAfter(end))
                .toList();
    }

    public Map<String, Long> mostActiveDoctors() {
        return appointmentRepo.findAll().stream()
                .collect(Collectors.groupingBy(a -> a.getDoctor().getName(), Collectors.counting()));
    }

    public Map<String, Long> mostDispensedDrugs() {
        return prescriptionRepo.findAll().stream()
                .collect(Collectors.groupingBy(p -> p.getDrug().getName(), Collectors.counting()));
    }

    public List<DrugDTO> outOfStockDrugs() {
        return drugRepo.findAll().stream()
                .filter(d -> !d.isAvailable())
                .map(d -> {
                    DrugDTO dto = new DrugDTO();
                    dto.setDrugId(d.getDrugId());
                    dto.setName(d.getName());
                    return dto;
                }).toList();
    }

    public List<BillDTO> unpaidBills() {
        return billRepo.findAll().stream()
                .filter(b -> b.getPaymentStatus() == org.example.hospital.enums.PaymentStatus.PENDING)
                .map(b -> {
                    BillDTO dto = new BillDTO();
                    dto.setBillId(b.getBillId());
                    dto.setPatientId(b.getPatient().getUserId());
                    dto.setPaymentStatus(b.getPaymentStatus());
                    return dto;
                }).toList();
    }
}
