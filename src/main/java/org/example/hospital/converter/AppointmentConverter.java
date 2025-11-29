package org.example.hospital.converter;

import org.example.hospital.dto.AppointmentDTO;
import org.example.hospital.entity.*;
import org.springframework.stereotype.Component;

@Component
public class AppointmentConverter implements Converter<Appointment, AppointmentDTO> {

    @Override
    public AppointmentDTO convertToDTO(Appointment entity, AppointmentDTO dto) {
        dto.setAppointmentId(entity.getApptId());
        dto.setSymptom(entity.getSymptom());
        dto.setStartTime(entity.getStartTime());
        dto.setEndTime(entity.getEndTime());
        dto.setStatus(entity.getStatus());
        dto.setDuration(entity.getDuration());

        if (entity.getDoctor() != null)
            dto.setDoctorId(entity.getDoctor().getUserId());

        if (entity.getPatient() != null)
            dto.setPatientId(entity.getPatient().getUserId());

        return dto;
    }

    @Override
    public Appointment convertToEntity(AppointmentDTO dto, Appointment entity) {
        entity.setApptId(dto.getAppointmentId());
        entity.setSymptom(dto.getSymptom());
        entity.setStartTime(dto.getStartTime());
        entity.setEndTime(dto.getEndTime());
        entity.setStatus(dto.getStatus());
        entity.setDuration(dto.getDuration());

        if (dto.getDoctorId() != null) {
            Doctor doctor = new Doctor();
            doctor.setUserId(dto.getDoctorId());
            entity.setDoctor(doctor);
        }

        if (dto.getPatientId() != null) {
            Patient patient = new Patient();
            patient.setUserId(dto.getPatientId());
            entity.setPatient(patient);
        }

        return entity;
    }
}
