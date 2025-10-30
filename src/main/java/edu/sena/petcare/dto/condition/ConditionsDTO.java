package edu.sena.petcare.dto.condition;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ConditionsDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;

    @NotBlank(message = "La descripción no puede estar vacía")
    private String description;
    
    @NotBlank(message = "El icono no puede estar vacio")
    private String icon;
}
