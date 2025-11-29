package org.example.hospital.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "prescriptions")
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pres_id")
    private Long presId;

    private String strength;
    private String dosageForm;
    private String sig;
    @Column(name = "quantity_to_dispense")
    private int quantityToDispense;
    private String route;
    private String frequency;
    private String duration;

    @Column(name = "prescription_date")
    private LocalDate prescriptionDate;

    @ManyToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    @ManyToOne
    @JoinColumn(name = "drug_id")
    private Drug drug;
}
