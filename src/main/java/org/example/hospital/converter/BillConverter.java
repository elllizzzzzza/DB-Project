package org.example.hospital.converter;

import org.example.hospital.dto.BillDTO;
import org.example.hospital.entity.Bill;
import org.example.hospital.entity.BillingOfficer;
import org.example.hospital.entity.Patient;
import org.springframework.stereotype.Component;

@Component
public class BillConverter implements Converter<Bill, BillDTO> {

    @Override
    public BillDTO convertToDTO(Bill entity, BillDTO dto) {
        dto.setBillId(entity.getBillId());
        dto.setPaymentStatus(entity.getPaymentStatus());
        dto.setDateIssued(entity.getDateIssued());
        if (entity.getPatient() != null) dto.setPatientId(entity.getPatient().getUserId());
        if (entity.getBillingOfficer() != null) dto.setBillingOfficerId(entity.getBillingOfficer().getUserId());
        return dto;
    }

    @Override
    public Bill convertToEntity(BillDTO dto, Bill entity) {
        entity.setBillId(dto.getBillId());
        entity.setPaymentStatus(dto.getPaymentStatus());
        entity.setDateIssued(dto.getDateIssued());

        if (dto.getPatientId() != null) {
            Patient patient = new Patient();
            patient.setUserId(dto.getPatientId());
            entity.setPatient(patient);
        }

        if (dto.getBillingOfficerId() != null) {
            BillingOfficer officer = new BillingOfficer();
            officer.setUserId(dto.getBillingOfficerId());
            entity.setBillingOfficer(officer);
        }

        return entity;
    }
}
