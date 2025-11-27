package org.example.hospital.service;

import org.example.hospital.dto.DrugDTO;

import java.util.List;

public interface DrugService {

    DrugDTO createDrug(DrugDTO dto);

    DrugDTO getDrugById(Long id);

    List<DrugDTO> getAllDrugs();

    DrugDTO updateDrug(Long id, DrugDTO dto);

    void deleteDrug(Long id);
}
