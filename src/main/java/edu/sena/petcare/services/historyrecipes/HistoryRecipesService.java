package edu.sena.petcare.services.historyrecipes;

import java.util.List;
import java.util.Optional;

import edu.sena.petcare.dto.historyrecipes.HistoryRecipesNewUpdateDTO;
import edu.sena.petcare.dto.historyrecipes.HistoryRecipesReadDTO;

public interface HistoryRecipesService {
    HistoryRecipesReadDTO createHistoryRecipe(HistoryRecipesNewUpdateDTO historyRecipeDTO);

    List<HistoryRecipesReadDTO> getAllHistoryRecipes();

    Optional<HistoryRecipesReadDTO> getHistoryRecipeById(Long id);

    HistoryRecipesReadDTO updateHistoryRecipe(Long id, HistoryRecipesNewUpdateDTO historyRecipeDTO);

    void deleteHistoryRecipe(Long id);

    // Métodos personalizados
    List<HistoryRecipesReadDTO> getActivePrescriptionsByPetId(Long petId);

    List<HistoryRecipesReadDTO> getAllPrescriptionsByPetId(Long petId);
}
