package org.example.hospital.converter;
import org.example.hospital.dto.AppointmentDTO;
import org.example.hospital.entity.Appointment;
import org.example.hospital.entity.Doctor;
import org.example.hospital.entity.Patient;
import org.springframework.stereotype.Component;
@Component
public class AppointmentConverter {
    public AppointmentDTO toDTO(Appointment appointment) {
        if (appointment == null) return null;
        AppointmentDTO dto = new AppointmentDTO();
        dto.setAppointmentId(appointment.getAppId());
        dto.setAppointmentDate(appointment.getStartTime());
        dto.setDuration(appointment.getDuration());
        if (appointment.getDoctor() != null) {
            dto.setDoctorId(appointment.getDoctor().getUserId());
        }
        if (appointment.getPatient() != null) {
            dto.setPatientId(appointment.getPatient().getUserId());
        }
        if (appointment.getStauts()!= null) {
            dto.setStatus(appointment.getStauts());
        }
        return dto;
    }
    public Appointment toEntity(AppointmentDTO dto, Doctor doctor, Patient patient) {
        if (dto == null) return null;
        Appointment a = new Appointment();
        a.setAppId(dto.getAppointmentId());
        a.setStartTime(dto.getAppointmentDate());
        a.setDuration(dto.getDuration());
        a.setDoctor(doctor);
        a.setPatient(patient);
        return a;
    }
}
