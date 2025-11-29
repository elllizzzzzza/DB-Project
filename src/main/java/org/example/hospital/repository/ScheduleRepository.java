package org.example.hospital.repository;

import org.example.hospital.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByDoctor_UserId(Long doctorId);
}
