package org.example.hospital.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "nurses")
public class Nurse extends User {
    @Column(name = "nurse_level")
    private String nurseLevel;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
