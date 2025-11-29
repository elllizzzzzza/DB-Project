package org.example.hospital.dto;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PrescriptionDTO {
    private Long presId;
    private Long drugId;
    private String drugName;

    private String strength;
    private String dosageForm;
    private String sig;
    private int quantityToDispense;
    private String route;
    private String frequency;
    private String duration;
    private LocalDate prescriptionDate;

    private Long appointmentId;
}

