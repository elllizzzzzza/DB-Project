package org.example.hospital.services;

import lombok.RequiredArgsConstructor;
import org.example.hospital.converter.DrugConverter;
import org.example.hospital.dto.DrugDTO;
import org.example.hospital.entity.Drug;
import org.example.hospital.repositories.DrugRepository;
import org.example.hospital.services.DrugService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DrugServiceImpl implements DrugService {

    private final DrugRepository drugRepository;
    private final DrugConverter drugConverter;

    @Override
    public DrugDTO createDrug(DrugDTO dto) {
        Drug drug = drugConverter.toEntity(dto);
        Drug saved = drugRepository.save(drug);
        return drugConverter.toDTO(saved);
    }

    @Override
    public DrugDTO getDrugById(Long id) {
        Drug drug = drugRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Drug not found with id: " + id));
        return drugConverter.toDTO(drug);
    }

    @Override
    public List<DrugDTO> getAllDrugs() {
        return drugRepository.findAll()
                .stream()
                .map(drugConverter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DrugDTO updateDrug(Long id, DrugDTO dto) {
        Drug existing = drugRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Drug not found with id: " + id));

        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());
        existing.setPrice(dto.getPrice());
        existing.setAvailable(dto.isAvailable());

        Drug updated = drugRepository.save(existing);
        return drugConverter.toDTO(updated);
    }

    @Override
    public void deleteDrug(Long id) {
        if (!drugRepository.existsById(id)) {
            throw new RuntimeException("Drug not found with id: " + id);
        }
        drugRepository.deleteById(id);
    }
}
