package org.example.hospital.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "doctors")
public class Doctor extends User {
    @Column(name="info")
    private String info;
    @Column(name="license_number")
    private String licenseNum;
    @Column(name="room_num")
    private String roomNum;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    @Column(name="schedule")
    private List<Schedule> schedule;

    @ManyToMany(mappedBy = "doctors")
    private List<Patient> patients;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Prescription> prescriptions;

    @OneToMany(mappedBy = "requestedByDoctor", cascade = CascadeType.ALL)
    private List<Procedure> procedures;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Appointment> appointments;

}
