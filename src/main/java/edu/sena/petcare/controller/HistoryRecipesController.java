package edu.sena.petcare.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.sena.petcare.dto.historyrecipes.HistoryRecipesNewUpdateDTO;
import edu.sena.petcare.dto.historyrecipes.HistoryRecipesReadDTO;
import edu.sena.petcare.services.historyrecipes.HistoryRecipesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/history-recipes")
@RequiredArgsConstructor
public class HistoryRecipesController {

    private final HistoryRecipesService historyRecipesService;

    @PostMapping
    public ResponseEntity<HistoryRecipesReadDTO> createHistoryRecipe(
            @Valid @RequestBody HistoryRecipesNewUpdateDTO historyRecipeDTO) {
        HistoryRecipesReadDTO created = historyRecipesService.createHistoryRecipe(historyRecipeDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<HistoryRecipesReadDTO>> getAllHistoryRecipes() {
        List<HistoryRecipesReadDTO> historyRecipes = historyRecipesService.getAllHistoryRecipes();
        return ResponseEntity.ok(historyRecipes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<HistoryRecipesReadDTO>> getHistoryRecipeById(@PathVariable Long id) {
        Optional<HistoryRecipesReadDTO> historyRecipe = historyRecipesService.getHistoryRecipeById(id);
        return ResponseEntity.ok(historyRecipe);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistoryRecipesReadDTO> updateHistoryRecipe(@PathVariable Long id,
            @Valid @RequestBody HistoryRecipesNewUpdateDTO historyRecipeDTO) {
        HistoryRecipesReadDTO updated = historyRecipesService.updateHistoryRecipe(id, historyRecipeDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHistoryRecipe(@PathVariable Long id) {
        historyRecipesService.deleteHistoryRecipe(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoints personalizados
    @GetMapping("/pet/{petId}/active")
    public ResponseEntity<List<HistoryRecipesReadDTO>> getActivePrescriptionsByPetId(@PathVariable Long petId) {
        List<HistoryRecipesReadDTO> activeRecipes = historyRecipesService.getActivePrescriptionsByPetId(petId);
        return ResponseEntity.ok(activeRecipes);
    }

    @GetMapping("/pet/{petId}")
    public ResponseEntity<List<HistoryRecipesReadDTO>> getAllPrescriptionsByPetId(@PathVariable Long petId) {
        List<HistoryRecipesReadDTO> allRecipes = historyRecipesService.getAllPrescriptionsByPetId(petId);
        return ResponseEntity.ok(allRecipes);
    }
}
