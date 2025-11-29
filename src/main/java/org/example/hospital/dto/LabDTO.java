package org.example.hospital.dto;

import lombok.Data;

import java.util.List;

@Data
public class LabDTO {
    private Long labId;
    private String name;
    private String location;
    private List<String> workingHours;
}

