package org.example.hospital.converter;

import org.example.hospital.dto.ProcedureDTO;
import org.example.hospital.entity.*;
import org.springframework.stereotype.Component;

@Component
public class ProcedureConverter implements Converter<Procedure, ProcedureDTO> {

    @Override
    public ProcedureDTO convertToDTO(Procedure entity, ProcedureDTO dto) {
        dto.setProcedureId(entity.getProcedureId());
        dto.setProcedureName(entity.getProcedureName());
        dto.setDate(entity.getDate());
        dto.setResult(entity.getResult());
        dto.setResultStatus(entity.getResultStatus());

        if (entity.getAppointment() != null)
            dto.setAppointmentId(entity.getAppointment().getApptId());

        if (entity.getLab() != null)
            dto.setLabId(entity.getLab().getLabId());

        return dto;
    }

    @Override
    public Procedure convertToEntity(ProcedureDTO dto, Procedure entity) {
        entity.setProcedureId(dto.getProcedureId());
        entity.setProcedureName(dto.getProcedureName());
        entity.setDate(dto.getDate());
        entity.setResult(dto.getResult());
        entity.setResultStatus(dto.getResultStatus());

        if (dto.getAppointmentId() != null) {
            Appointment appointment = new Appointment();
            appointment.setApptId(dto.getAppointmentId());
            entity.setAppointment(appointment);
        }

        if (dto.getLabId() != null) {
            Lab lab = new Lab();
            lab.setLabId(dto.getLabId());
            entity.setLab(lab);
        }

        return entity;
    }
}
