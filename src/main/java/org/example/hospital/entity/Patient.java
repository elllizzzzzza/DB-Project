package org.example.hospital.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.example.hospital.enums.BloodType;
import org.example.hospital.enums.Gender;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "patients")
public class Patient extends User {
    @Column(name="date_of_birth")
    private LocalDate dateOfBirth;
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name="blood_type")
    private BloodType bloodType;
    @Column(name="id_card")
    private String idCard;
    @Column(name="med_insurance")
    private String medicalInsurance;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Prescription> prescriptions;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Procedure> procedures;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Bill> bills;
}
