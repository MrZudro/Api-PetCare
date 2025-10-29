package edu.sena.petcare.dto.service;

import edu.sena.petcare.models.enums.StatusService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ServiceNewUpdateDTO {
    @NotNull(message = "El estado no puede ser nulo")
    private StatusService status;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;

    @NotBlank(message = "La descripción no puede estar vacía")
    private String description;

    private String picture;
}
