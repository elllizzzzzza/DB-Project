package org.example.hospital.service;

import org.example.hospital.converter.BillConverter;
import org.example.hospital.dto.BillDTO;
import org.example.hospital.entity.Bill;
import org.example.hospital.entity.Patient;
import org.example.hospital.entity.BillingOfficer;
import org.example.hospital.repository.BillRepository;
import org.example.hospital.repository.PatientRepository;
import org.example.hospital.repository.BillingOfficerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BillingService {

    private final BillRepository billRepo;
    private final PatientRepository patientRepo;
    private final BillingOfficerRepository officerRepo;
    private final BillConverter converter;

    public BillingService(BillRepository billRepo, PatientRepository patientRepo,
                          BillingOfficerRepository officerRepo, BillConverter converter) {
        this.billRepo = billRepo;
        this.patientRepo = patientRepo;
        this.officerRepo = officerRepo;
        this.converter = converter;
    }

    public BillDTO createBill(BillDTO dto) {
        Bill bill = new Bill();
        converter.convertToEntity(dto, bill);

        Patient patient = patientRepo.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        BillingOfficer officer = officerRepo.findById(dto.getBillingOfficerId())
                .orElseThrow(() -> new RuntimeException("Billing officer not found"));

        bill.setPatient(patient);
        bill.setBillingOfficer(officer);

        billRepo.save(bill);
        return converter.convertToDTO(bill, new BillDTO());
    }

    public void markPaid(Long billId) {
        billRepo.findById(billId).ifPresent(bill -> {
            bill.setPaymentStatus(org.example.hospital.enums.PaymentStatus.PAID);
            billRepo.save(bill);
        });
    }

    public List<BillDTO> getUnpaidBills() {
        return billRepo.findAll().stream()
                .filter(b -> b.getPaymentStatus() == org.example.hospital.enums.PaymentStatus.PENDING)
                .map(b -> converter.convertToDTO(b, new BillDTO()))
                .toList();
    }
}
