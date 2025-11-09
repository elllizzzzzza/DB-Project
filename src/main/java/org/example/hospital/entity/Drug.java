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
    private Long drugId;

    private String name;
    private String description;
    private String type;
    private double price;
    private boolean isAvailable;

    @ManyToMany(mappedBy = "drugs")
    private List<Pharmacist> pharmacists;
}

