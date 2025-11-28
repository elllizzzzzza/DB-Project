package org.example.hospital.repositories;
import org.example.hospital.entity.LabTechnician;
import org.springframework.data.jpa.repository.JpaRepository;
public interface LabTechnicianRepository extends JpaRepository<LabTechnician, Long> {
}
