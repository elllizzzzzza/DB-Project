package org.example.hospital.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "prescriptions")
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pres_id")
    private Long presId;
    @Column(name="dosage")
    private String dosage;
    @Column(name="frequency")
    private String frequency;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "pharmacist_id")
    private Pharmacist dispensedBy;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Procedure procedure;

    @OneToMany(mappedBy = "prescription", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Drug> items = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name="drug_id")
    private Drug drug;
    @Transient
    private double totalPrice;

}
