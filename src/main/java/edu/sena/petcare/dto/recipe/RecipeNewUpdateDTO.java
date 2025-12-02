package edu.sena.petcare.dto.recipe;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RecipeNewUpdateDTO {

    @NotBlank(message = "El nombre del medicamento no puede estar vacío")
    private String medicationName;

    private String description;

    private String dosageInstructions;

    private String sideEffects;

    private String contraindications;
}
