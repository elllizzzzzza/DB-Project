package org.example.hospital.repositories;
import org.example.hospital.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
