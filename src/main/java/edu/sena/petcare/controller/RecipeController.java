package edu.sena.petcare.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.sena.petcare.dto.recipe.RecipeNewUpdateDTO;
import edu.sena.petcare.dto.recipe.RecipeReadDTO;
import edu.sena.petcare.services.recipe.RecipeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/recipes")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @PostMapping
    public ResponseEntity<RecipeReadDTO> createRecipe(@Valid @RequestBody RecipeNewUpdateDTO recipeDTO) {
        RecipeReadDTO created = recipeService.createRecipe(recipeDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RecipeReadDTO>> getAllRecipes() {
        List<RecipeReadDTO> recipes = recipeService.getAllRecipes();
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<RecipeReadDTO>> getRecipeById(@PathVariable Long id) {
        Optional<RecipeReadDTO> recipe = recipeService.getRecipeById(id);
        return ResponseEntity.ok(recipe);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecipeReadDTO> updateRecipe(@PathVariable Long id,
            @Valid @RequestBody RecipeNewUpdateDTO recipeDTO) {
        RecipeReadDTO updated = recipeService.updateRecipe(id, recipeDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
        return ResponseEntity.noContent().build();
    }
}
