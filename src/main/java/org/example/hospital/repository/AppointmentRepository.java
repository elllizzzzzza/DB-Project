package org.example.hospital.repository;
import org.example.hospital.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDoctor_UserId(Long doctorId);
    List<Appointment> findByPatient_UserId(Long patientId);
}
