package org.example.hospital.dto;

import lombok.Data;

@Data
public class AdminDTO {
    private Long userId;
    private String username;
    private String email;
    private String name;
    private String surname;
    private String phoneNum;
}
