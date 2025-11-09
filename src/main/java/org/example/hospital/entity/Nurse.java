package org.example.hospital.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "nurses")
public class Nurse extends User {

    private String nurseLevel;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
