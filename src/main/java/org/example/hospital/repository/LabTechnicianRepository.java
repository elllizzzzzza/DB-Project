package org.example.hospital.repository;

import org.example.hospital.entity.LabTechnician;
import org.example.hospital.entity.Procedure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LabTechnicianRepository extends JpaRepository<LabTechnician, Long> {
    List<Procedure> findByLab_LabId(Long labId);
}
