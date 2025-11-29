package org.example.hospital.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LabTechnicianDTO {
    private Long userId;
    private String username;
    private String email;
    private String name;
    private String surname;
    private String phoneNum;
    private LocalDate employmentStartDate;
    private Long labId;
}
