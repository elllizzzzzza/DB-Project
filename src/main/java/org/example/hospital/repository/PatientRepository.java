package org.example.hospital.repository;
import org.example.hospital.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
