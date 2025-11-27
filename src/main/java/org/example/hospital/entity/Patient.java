package org.example.hospital.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.example.hospital.enums.BloodType;

import java.util.List;

@Data
@Entity
@Table(name = "patients")
public class Patient extends User {
    @Column(name="date_of_birth")
    private String dateOfBirth;
    @Column(name="gender")
    private String gender;
    @Column(name="address")
    private String address;
    @Enumerated(EnumType.STRING)
    @Column(name="blood_type")
    private BloodType bloodType;
    @Column(name="id_card")
    private String idCard;
    @Column(name="med_insurance")
    private String medicalInsurance;

    @ManyToMany
    @JoinTable(
            name = "doctor_patient",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id")
    )
    private List<Doctor> doctors;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Prescription> prescriptions;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Procedure> procedures;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Bill> bills;
}
