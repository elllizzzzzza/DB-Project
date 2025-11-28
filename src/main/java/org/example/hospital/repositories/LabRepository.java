package org.example.hospital.repositories;

import org.example.hospital.entity.Lab;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabRepository  extends JpaRepository<Lab, Long> {
}
