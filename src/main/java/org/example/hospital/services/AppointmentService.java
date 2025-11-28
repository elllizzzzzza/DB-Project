package org.example.hospital.services;
import org.example.hospital.dto.AppointmentDTO;
import java.util.List;
public interface AppointmentService {
    AppointmentDTO createAppointment(AppointmentDTO dto);
    AppointmentDTO getAppointmentById(Long id);
    List<AppointmentDTO> getAllAppointments();
    AppointmentDTO updateAppointment(Long id, AppointmentDTO dto);
    void deleteAppointment(Long id);
}
