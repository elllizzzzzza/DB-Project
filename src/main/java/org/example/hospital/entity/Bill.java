package org.example.hospital.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.example.hospital.enums.PaymentStatus;

import java.time.LocalDate;


@Data
@Entity
@Table(name = "bills")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billId;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;
    private LocalDate dateIssued;
    @Transient
    private double totalAmount;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "billing_officer_id")
    private BillingOfficer billingOfficer;
}

