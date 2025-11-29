package org.example.hospital.repository;
import org.example.hospital.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    List<Prescription> findByAppointment_Patient_UserId(Long patientId);
}