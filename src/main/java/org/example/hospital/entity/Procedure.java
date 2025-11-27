package org.example.hospital.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "procedure")
public class Procedure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="test_id")
    private Long testId;
    @Column(name= "test_name")
    private String testName;
    @Column(name = "date")
    private String date;
    @Column(name = "result")
    private String result;
    @Column(name = "resultStatus")
    private String resultStatus;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "lab_id")
    private Lab lab;

    @ManyToOne
    @JoinColumn(name = "lab_technician_id")
    private LabTechnician labTechnician;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor requestedByDoctor;
}
