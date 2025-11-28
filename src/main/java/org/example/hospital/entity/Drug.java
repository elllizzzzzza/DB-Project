package org.example.hospital.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "drugs")
public class Drug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drug_id")
    private Long drugId;
    private String name;
    private String description;
    private double price;
    @Column(name = "is_available")
    private boolean isAvailable;

    @ManyToMany(mappedBy = "drugs")
    private List<Pharmacist> pharmacists;

    @OneToMany(mappedBy = "drug", cascade = CascadeType.ALL)
    private List<Prescription> prescriptions;
}

