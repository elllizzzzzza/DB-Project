package org.example.hospital.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "pharmacists")
public class Pharmacist extends User {
    @Column(name = "license_number")
    private String licenseNum;
    @Column(name = "pharmacy_section")
    private String pharmacySection;

    @ManyToMany
    @JoinTable(
            name = "pharmacist_drug",
            joinColumns = @JoinColumn(name = "pharmacist_id"),
            inverseJoinColumns = @JoinColumn(name = "drug_id")
    )
    private List<Drug> drugs;
}