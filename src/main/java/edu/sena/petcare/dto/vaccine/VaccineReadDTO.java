package edu.sena.petcare.dto.vaccine;

import lombok.Data;

@Data
public class VaccineReadDTO {
    private Long id;
    private String name;
    private String description;
    private String manufacturer;
    private Integer recommendedAgeMonths;
    private Integer validityMonths;
}
