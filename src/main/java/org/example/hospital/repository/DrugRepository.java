package org.example.hospital.repository;
import org.example.hospital.entity.Drug;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DrugRepository extends JpaRepository<Drug, Long> {
    List<Drug> findByIsAvailableTrue();
}