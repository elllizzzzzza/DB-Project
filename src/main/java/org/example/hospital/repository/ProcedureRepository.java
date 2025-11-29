package org.example.hospital.repository;
import org.example.hospital.entity.Procedure;
import org.example.hospital.entity.Schedule;
import org.example.hospital.enums.ProcedureResultStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProcedureRepository extends JpaRepository<Procedure, Long> {
    List<Procedure> findByAppointment_Patient_UserId(Long patientId);
    List<Procedure> findByLab_LabId(Long labId);
    List<Procedure> findByLab_LabIdAndResultStatus(Long labId, ProcedureResultStatus status);
}