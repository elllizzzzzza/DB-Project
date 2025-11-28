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
    @Column(name="lab_id")
    private Long labId;
    private String name;
    private String location;

    @ElementCollection
    @Column(name="working_hours")
    private List<String> workingHours;

    @OneToMany(mappedBy = "lab", cascade = CascadeType.ALL)
    private List<LabTechnician> technicians;

    @OneToMany(mappedBy = "lab", cascade = CascadeType.ALL)
    private List<Procedure> procedures;
}
