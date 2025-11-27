package org.example.hospital.repositories;
import org.example.hospital.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
}