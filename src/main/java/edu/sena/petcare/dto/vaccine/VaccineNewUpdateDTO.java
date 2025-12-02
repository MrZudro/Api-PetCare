package edu.sena.petcare.dto.vaccine;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class VaccineNewUpdateDTO {

    @NotBlank(message = "El nombre de la vacuna no puede estar vacío")
    private String name;

    private String description;

    private String manufacturer;

    private Integer recommendedAgeMonths;

    private Integer validityMonths;
}
