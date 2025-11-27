package org.example.hospital.dto;
import lombok.Data;
@Data
public class PrescriptionDTO {
    private Long presId;
    private String dosage;
    private String frequency;
    private Long drugId;
    private Long patientId;
    private Long doctorId;
    private Long pharmacistId;
    private Long procedureId;
    private Double totalPrice;
}
