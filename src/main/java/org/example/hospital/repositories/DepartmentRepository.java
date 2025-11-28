package org.example.hospital.repositories;

import org.example.hospital.entity.Appointment;
import org.example.hospital.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
