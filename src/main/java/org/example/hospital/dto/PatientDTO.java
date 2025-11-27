package org.example.hospital.dto;
import lombok.Data;
@Data
public class PatientDTO {
    private Long patientId;
    private String firstName;
    private String lastName;
    private String idCard;
    private String gender;
    private String bloodType;
}
