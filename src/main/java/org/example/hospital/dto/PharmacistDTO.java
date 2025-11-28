package org.example.hospital.dto;

import lombok.Data;

import java.util.List;

@Data
public class PharmacistDTO {
    private Long userId;
    private String username;
    private String email;
    private String name;
    private String surname;
    private String phoneNum;
    private List<Long> drugIds;
}
