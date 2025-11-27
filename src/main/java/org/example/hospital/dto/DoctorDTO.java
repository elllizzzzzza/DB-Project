package org.example.hospital.dto;

import lombok.Data;

@Data
public class DoctorDTO {
    private Long userId;
    private String username;
    private String email;
    private String name;
    private String surname;
    private String phoneNum;
    //private String role;
    private String info;
    private String licenseNumber;
    private String roomNumber;
    private Long departmentId;
}
