package org.example.hospital.dto;
import lombok.Data;
import org.example.hospital.enums.ProcedureResultStatus;

import java.time.LocalDate;
@Data
public class ProcedureDTO {
    private Long procedureId;
    private String testName;
    private LocalDate date;
    private String result;
    private ProcedureResultStatus resultStatus;
    private Long appointmentId;
    private Long labId;
}
