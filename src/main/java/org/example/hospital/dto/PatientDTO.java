package org.example.hospital.dto;

import lombok.Data;
import org.example.hospital.enums.BloodType;
import org.example.hospital.enums.Gender;

import java.time.LocalDate;

@Data
public class PatientDTO {
    private Long userId;
    private String username;
    private String email;
    private String name;
    private String surname;
    private String phoneNum;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String address;
    private BloodType bloodType;
    private String idCard;
    private String medicalInsurance;
}

