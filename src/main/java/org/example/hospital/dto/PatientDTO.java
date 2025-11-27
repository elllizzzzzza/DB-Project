package org.example.hospital.dto;

import lombok.Data;

@Data
public class PatientDTO {
    private Long userId;
    private String username;
    private String email;
    private String name;
    private String surname;
    private String phoneNum;
    private String role;
    private String dateOfBirth;
    private String gender;
    private String address;
    private String bloodType;
    private String idCard;
    private String medicalInsurance;
}
