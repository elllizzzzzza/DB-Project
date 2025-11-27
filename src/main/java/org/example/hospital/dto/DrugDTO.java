package org.example.hospital.dto;
import lombok.Data;
@Data
public class DrugDTO {
    private Long drugId;
    private String name;
    private String description;
    private double price;
    private boolean available;
}
