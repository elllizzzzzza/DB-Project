package org.example.hospital.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.example.hospital.enums.ProcedureResultStatus;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "procedures")
public class Procedure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="procedure_id")
    private Long procedureId;

    @Column(name= "procedure_name")
    private String procedureName;

    @Column(name="date")
    private LocalDate date;

    private String result;

    @Enumerated(EnumType.STRING)
    @Column(name = "result_status")
    private ProcedureResultStatus resultStatus;

    @ManyToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    @ManyToOne
    @JoinColumn(name = "lab_id")
    private Lab lab;
}
