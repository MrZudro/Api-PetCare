package edu.sena.petcare.dto.recipe;

import lombok.Data;

@Data
public class RecipeReadDTO {
    private Long id;
    private String medicationName;
    private String description;
    private String dosageInstructions;
    private String sideEffects;
    private String contraindications;
}
