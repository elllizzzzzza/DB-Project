package org.example.hospital.dto;
import lombok.Data;
@Data
public class DoctorDTO {
    private Long doctorId;
    private String firstName;
    private String lastName;
    private String licenseNumber;
    private String roomNumber;
    private String info;
    private Long departmentId;
}
