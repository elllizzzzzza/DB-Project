package org.example.hospital.converter;

import org.example.hospital.dto.ProcedureDTO;
import org.example.hospital.entity.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ProcedureConverter {

    public ProcedureDTO toDTO(Procedure procedure) {
        if (procedure == null) return null;

        ProcedureDTO dto = new ProcedureDTO();
        dto.setProcedureId(procedure.getTestId());
        dto.setTestName(procedure.getTestName());
        dto.setDate(LocalDate.parse((procedure.getDate())));
        dto.setResult(procedure.getResult());

        if (procedure.getResultStatus() != null) {
            dto.setResultStatus(procedure.getResultStatus());
        }

        if (procedure.getPatient() != null) {
            dto.setPatientId(procedure.getPatient().getUserId());
        }
        if (procedure.getLab() != null) {
            dto.setLabId(procedure.getLab().getLabId());
        }
        if (procedure.getLabTechnician() != null) {
            dto.setLabTechnicianId(procedure.getLabTechnician().getUserId());
        }
        if (procedure.getDoctor() != null) {
            dto.setDoctorId(procedure.getDoctor().getUserId());
        }

        return dto;
    }

    public Procedure toEntity(ProcedureDTO dto,
                              Patient patient,
                              Lab lab,
                              LabTechnician labTechnician,
                              Doctor doctor) {
        if (dto == null) return null;

        Procedure p = new Procedure();
        p.setTestId(dto.getProcedureId());
        p.setTestName(dto.getTestName());
        p.setDate(String.valueOf(dto.getDate()));
        p.setResult(dto.getResult());
        p.setPatient(patient);
        p.setLab(lab);
        p.setLabTechnician(labTechnician);
        p.setDoctor(doctor);
        return p;
    }
}
