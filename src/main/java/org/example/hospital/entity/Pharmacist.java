package org.example.hospital.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "pharmacists")
public class Pharmacist extends User {

    private String licenseNum;
    private String pharmacySection;

    @ManyToMany
    @JoinTable(
            name = "pharmacist_drug",
            joinColumns = @JoinColumn(name = "pharmacist_id"),
            inverseJoinColumns = @JoinColumn(name = "drug_id")
    )
    private List<Drug> drugs;


    @OneToMany(mappedBy = "dispensedBy", cascade = CascadeType.ALL)
    private List<Prescription> prescriptions;
}

