package org.example.hospital.dto;

import lombok.Data;
import org.example.hospital.enums.PaymentStatus;

import java.time.LocalDate;

@Data
public class BillDTO {
    private Long billId;
    private PaymentStatus paymentStatus;
    private LocalDate dateIssued;
    private double totalAmount;
    private Long patientId;
    private Long billingOfficerId;
}
