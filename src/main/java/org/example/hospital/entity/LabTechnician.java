package org.example.hospital.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "lab_technicians")
public class LabTechnician extends User {
    @Column(name= "employment_start_date")
    private LocalDate employmentStartDate;

    @ManyToOne
    @JoinColumn(name = "lab_id")
    private Lab lab;

}
