package edu.sena.petcare.mapper;

import org.springframework.stereotype.Component;
import edu.sena.petcare.dto.recipe.RecipeNewUpdateDTO;
import edu.sena.petcare.dto.recipe.RecipeReadDTO;
import edu.sena.petcare.models.Recipe;
import edu.sena.petcare.utility.ListaMappeo;
import java.util.List;

@Component
public class RecipeMapper {

    public RecipeReadDTO toReadDTO(Recipe recipe) {
        if (recipe == null) {
            return null;
        }

        RecipeReadDTO dto = new RecipeReadDTO();
        dto.setId(recipe.getId());
        dto.setMedicationName(recipe.getMedicationName());
        dto.setDescription(recipe.getDescription());
        dto.setDosageInstructions(recipe.getDosageInstructions());
        dto.setSideEffects(recipe.getSideEffects());
        dto.setContraindications(recipe.getContraindications());
        return dto;
    }

    public Recipe toEntity(RecipeNewUpdateDTO dto) {
        if (dto == null) {
            return null;
        }

        Recipe recipe = new Recipe();
        recipe.setMedicationName(dto.getMedicationName());
        recipe.setDescription(dto.getDescription());
        recipe.setDosageInstructions(dto.getDosageInstructions());
        recipe.setSideEffects(dto.getSideEffects());
        recipe.setContraindications(dto.getContraindications());
        return recipe;
    }

    public void updateEntity(RecipeNewUpdateDTO dto, Recipe recipe) {
        if (dto == null || recipe == null) {
            return;
        }
        if (dto.getMedicationName() != null)
            recipe.setMedicationName(dto.getMedicationName());
        if (dto.getDescription() != null)
            recipe.setDescription(dto.getDescription());
        if (dto.getDosageInstructions() != null)
            recipe.setDosageInstructions(dto.getDosageInstructions());
        if (dto.getSideEffects() != null)
            recipe.setSideEffects(dto.getSideEffects());
        if (dto.getContraindications() != null)
            recipe.setContraindications(dto.getContraindications());
    }

    public List<RecipeReadDTO> toReadDtoList(List<Recipe> entities) {
        return ListaMappeo.toDtoList(entities, this::toReadDTO);
    }
}
