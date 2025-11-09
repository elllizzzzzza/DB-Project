package org.example.hospital.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "labs")
public class Lab {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long labId;

    private String name;
    private String location;

    @ElementCollection
    private List<String> workingHours;

    @OneToMany(mappedBy = "lab", cascade = CascadeType.ALL)
    private List<LabTechnician> technicians;

    @OneToMany(mappedBy = "lab", cascade = CascadeType.ALL)
    private List<LabTest> labTests;
}
