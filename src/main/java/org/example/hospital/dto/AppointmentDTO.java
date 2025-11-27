package org.example.hospital.dto;
import lombok.Data;
import java.time.LocalDateTime;
@Data
public class AppointmentDTO {
    private Long appointmentId;
    private LocalDateTime appointmentDate;
    private Integer duration;
    private Long doctorId;
    private Long patientId;
    private String status;
}