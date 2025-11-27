package org.example.hospital.converter;
import org.example.hospital.dto.PrescriptionDTO;
import org.example.hospital.entity.*;
import org.springframework.stereotype.Component;
@Component
public class PrescriptionConverter {
    public PrescriptionDTO toDTO(Prescription prescription) {
        if (prescription == null) return null;
        PrescriptionDTO dto = new PrescriptionDTO();
        dto.setPresId(prescription.getPresId());
        dto.setDosage(prescription.getDosage());
        dto.setFrequency(prescription.getFrequency());
        if (prescription.getDrug() != null) {
            dto.setDrugId(prescription.getDrug().getDrugId());
        }
        if (prescription.getPatient() != null) {
            dto.setPatientId(prescription.getPatient().getUserId());
        }
        if (prescription.getDoctor() != null) {
            dto.setDoctorId(prescription.getDoctor().getUserId());
        }
        if (prescription.getDispensedBy() != null) {
            dto.setPharmacistId(prescription.getDispensedBy().getUserId());
        }
        if (prescription.getProcedure() != null) {
            dto.setProcedureId(prescription.getProcedure().getTestId());
        }
        dto.setTotalPrice(prescription.getTotalPrice());
        return dto;
    }
    public Prescription toEntity(PrescriptionDTO dto,
                                 Drug drug,
                                 Patient patient,
                                 Doctor doctor,
                                 Pharmacist pharmacist,
                                 Procedure procedure) {
        if (dto == null) return null;
        Prescription p = new Prescription();
        p.setPresId(dto.getPresId());
        p.setDosage(dto.getDosage());
        p.setFrequency(dto.getFrequency());
        p.setDrug(drug);
        p.setPatient(patient);
        p.setDoctor(doctor);
        p.setDispensedBy(pharmacist);
        p.setProcedure(procedure);
        return p;
    }
}
