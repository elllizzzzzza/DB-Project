package org.example.hospital.repositories;
import org.example.hospital.entity.Procedure;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProcedureRepository extends JpaRepository<Procedure, Long> {
}