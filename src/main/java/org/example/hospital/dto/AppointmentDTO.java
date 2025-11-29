package org.example.hospital.dto;
import lombok.Data;
import java.time.LocalDateTime;
@Data
public class AppointmentDTO {
    private Long appointmentId;
    private String symptom;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;
    private int duration;

    private Long doctorId;
    private String doctorName;
    private Long patientId;
    private String patientName;
}