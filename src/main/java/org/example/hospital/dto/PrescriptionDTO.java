package org.example.hospital.dto;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PrescriptionDTO {
    private Long presId;
    private String dosage;
    private String frequency;
    private String sig;
    private int quantityToDispense;
    private String route;
    private LocalDate prescriptionDate;
    private String duration;

    private Long appointmentId;
}
