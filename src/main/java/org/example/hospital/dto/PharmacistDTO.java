package org.example.hospital.dto;

import lombok.Data;

@Data
public class PharmacistDTO {
    private Long pharmacistId;
    private String firstName;
    private String lastName;
    private String employmentStartDate;
}
