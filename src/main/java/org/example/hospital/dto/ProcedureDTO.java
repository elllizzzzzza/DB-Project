package org.example.hospital.dto;
import lombok.Data;
import java.time.LocalDate;
@Data
public class ProcedureDTO {
    private Long procedureId;
    private String testName;
    private LocalDate date;
    private String result;
    private String resultStatus;
    private Long patientId;
    private Long labId;
    private Long labTechnicianId;
    private Long doctorId;
}
