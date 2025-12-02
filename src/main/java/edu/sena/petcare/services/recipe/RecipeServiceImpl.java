package edu.sena.petcare.services.recipe;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.sena.petcare.dto.recipe.RecipeNewUpdateDTO;
import edu.sena.petcare.dto.recipe.RecipeReadDTO;
import edu.sena.petcare.mapper.RecipeMapper;
import edu.sena.petcare.models.Recipe;
import edu.sena.petcare.repositories.RecipeRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;

    @Override
    public RecipeReadDTO createRecipe(RecipeNewUpdateDTO recipeDTO) {
        Recipe recipe = recipeMapper.toEntity(recipeDTO);
        Recipe saved = recipeRepository.save(recipe);
        return recipeMapper.toReadDTO(saved);
    }

    @Override
    public List<RecipeReadDTO> getAllRecipes() {
        List<Recipe> recipes = recipeRepository.findAll();
        return recipeMapper.toReadDtoList(recipes);
    }

    @Override
    public Optional<RecipeReadDTO> getRecipeById(Long id) {
        return recipeRepository.findById(id)
                .map(recipeMapper::toReadDTO);
    }

    @Override
    public RecipeReadDTO updateRecipe(Long id, RecipeNewUpdateDTO recipeDTO) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recipe not found with id: " + id));

        recipeMapper.updateEntity(recipeDTO, recipe);
        Recipe updated = recipeRepository.save(recipe);
        return recipeMapper.toReadDTO(updated);
    }

    @Override
    public void deleteRecipe(Long id) {
        if (!recipeRepository.existsById(id)) {
            throw new RuntimeException("Recipe not found with id: " + id);
        }
        recipeRepository.deleteById(id);
    }
}
