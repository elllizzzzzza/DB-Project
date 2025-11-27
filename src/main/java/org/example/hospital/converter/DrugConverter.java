package org.example.hospital.converter;
import org.example.hospital.dto.DrugDTO;
import org.example.hospital.entity.Drug;
import org.springframework.stereotype.Component;
@Component
public class DrugConverter {
    public DrugDTO toDTO(Drug drug) {
        if (drug == null) return null;
        DrugDTO dto = new DrugDTO();
        dto.setDrugId(drug.getDrugId());
        dto.setName(drug.getName());
        dto.setDescription(drug.getDescription());
        dto.setPrice(drug.getPrice());
        dto.setAvailable(drug.isAvailable());
        return dto;
    }
    public Drug toEntity(DrugDTO dto) {
        if (dto == null) return null;
        Drug drug = new Drug();
        drug.setDrugId(dto.getDrugId());
        drug.setName(dto.getName());
        drug.setDescription(dto.getDescription());
        drug.setPrice(dto.getPrice());
        drug.setAvailable(dto.isAvailable());
        return drug;
    }
}
