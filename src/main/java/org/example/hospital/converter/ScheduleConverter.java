package org.example.hospital.converter;

import org.example.hospital.dto.ScheduleDTO;
import org.example.hospital.entity.Doctor;
import org.example.hospital.entity.Schedule;
import org.springframework.stereotype.Component;

@Component
public class ScheduleConverter implements Converter<Schedule, ScheduleDTO> {

    @Override
    public ScheduleDTO convertToDTO(Schedule entity, ScheduleDTO dto) {
        dto.setScheduleId(entity.getScheduleId());
        dto.setDayOfWeek(entity.getDayOfWeek());
        dto.setStartTime(entity.getStartTime());
        dto.setEndTime(entity.getEndTime());
        if (entity.getDoctor() != null) dto.setDoctorId(entity.getDoctor().getUserId());
        return dto;
    }

    @Override
    public Schedule convertToEntity(ScheduleDTO dto, Schedule entity) {
        entity.setScheduleId(dto.getScheduleId());
        entity.setDayOfWeek(dto.getDayOfWeek());
        entity.setStartTime(dto.getStartTime());
        entity.setEndTime(dto.getEndTime());

        if (dto.getDoctorId() != null) {
            Doctor doctor = new Doctor();
            doctor.setUserId(dto.getDoctorId());
            entity.setDoctor(doctor);
        }

        return entity;
    }
}
