package edu.sena.petcare.services.recipe;

import java.util.List;
import java.util.Optional;

import edu.sena.petcare.dto.recipe.RecipeNewUpdateDTO;
import edu.sena.petcare.dto.recipe.RecipeReadDTO;

public interface RecipeService {
    RecipeReadDTO createRecipe(RecipeNewUpdateDTO recipeDTO);

    List<RecipeReadDTO> getAllRecipes();

    Optional<RecipeReadDTO> getRecipeById(Long id);

    RecipeReadDTO updateRecipe(Long id, RecipeNewUpdateDTO recipeDTO);

    void deleteRecipe(Long id);
}
