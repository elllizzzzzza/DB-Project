package org.example.hospital.converter;

import org.example.hospital.dto.PrescriptionDTO;
import org.example.hospital.entity.*;
import org.springframework.stereotype.Component;

@Component
public class PrescriptionConverter implements Converter<Prescription, PrescriptionDTO> {

    @Override
    public PrescriptionDTO convertToDTO(Prescription entity, PrescriptionDTO dto) {
        dto.setPresId(entity.getPresId());
        dto.setStrength(entity.getStrength());
        dto.setDosageForm(entity.getDosageForm());
        dto.setSig(entity.getSig());
        dto.setQuantityToDispense(entity.getQuantityToDispense());
        dto.setRoute(entity.getRoute());
        dto.setFrequency(entity.getFrequency());
        dto.setDuration(entity.getDuration());
        dto.setPrescriptionDate(entity.getPrescriptionDate());

        if (entity.getAppointment() != null)
            dto.setAppointmentId(entity.getAppointment().getApptId());

        if (entity.getDrug() != null) {
            dto.setDrugId(entity.getDrug().getDrugId());
            dto.setDrugName(entity.getDrug().getName()); // DTO only
        }

        return dto;
    }

    @Override
    public Prescription convertToEntity(PrescriptionDTO dto, Prescription entity) {
        entity.setPresId(dto.getPresId());
        entity.setStrength(dto.getStrength());
        entity.setDosageForm(dto.getDosageForm());
        entity.setSig(dto.getSig());
        entity.setQuantityToDispense(dto.getQuantityToDispense());
        entity.setRoute(dto.getRoute());
        entity.setFrequency(dto.getFrequency());
        entity.setDuration(dto.getDuration());
        entity.setPrescriptionDate(dto.getPrescriptionDate());

        if (dto.getAppointmentId() != null) {
            Appointment appointment = new Appointment();
            appointment.setApptId(dto.getAppointmentId());
            entity.setAppointment(appointment);
        }

        if (dto.getDrugId() != null) {
            Drug drug = new Drug();
            drug.setDrugId(dto.getDrugId());
            entity.setDrug(drug);
        }

        return entity;
    }
}
