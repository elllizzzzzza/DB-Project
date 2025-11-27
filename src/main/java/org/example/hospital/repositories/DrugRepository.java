package org.example.hospital.repositories;
import org.example.hospital.entity.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
public interface DrugRepository extends JpaRepository<Drug, Long> {
}