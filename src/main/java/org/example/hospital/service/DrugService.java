package org.example.hospital.service;

import org.example.hospital.converter.DrugConverter;
import org.example.hospital.dto.DrugDTO;
import org.example.hospital.entity.Drug;
import org.example.hospital.repository.DrugRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DrugService {

    private final DrugRepository repo;
    private final DrugConverter converter;

    public DrugService(DrugRepository repo, DrugConverter converter) {
        this.repo = repo;
        this.converter = converter;
    }

    public DrugDTO addDrug(DrugDTO dto) {
        Drug drug = new Drug();
        converter.convertToEntity(dto, drug);
        repo.save(drug);
        return converter.convertToDTO(drug, new DrugDTO());
    }

    public List<DrugDTO> getAvailableDrugs() {
        return repo.findByIsAvailableTrue().stream()
                .map(d -> converter.convertToDTO(d, new DrugDTO()))
                .toList();
    }

    public void markOutOfStock(Long drugId) {
        repo.findById(drugId).ifPresent(drug -> {
            drug.setAvailable(false);
            repo.save(drug);
        });
    }
}
