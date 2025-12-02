package edu.sena.petcare.services.historyrecipes;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.sena.petcare.dto.historyrecipes.HistoryRecipesNewUpdateDTO;
import edu.sena.petcare.dto.historyrecipes.HistoryRecipesReadDTO;
import edu.sena.petcare.mapper.HistoryRecipesMapper;
import edu.sena.petcare.models.HistoryRecipes;
import edu.sena.petcare.repositories.HistoryRecipesRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class HistoryRecipesServiceImpl implements HistoryRecipesService {

    private final HistoryRecipesRepository historyRecipesRepository;
    private final HistoryRecipesMapper historyRecipesMapper;

    @Override
    public HistoryRecipesReadDTO createHistoryRecipe(HistoryRecipesNewUpdateDTO historyRecipeDTO) {
        HistoryRecipes historyRecipe = historyRecipesMapper.toEntity(historyRecipeDTO);
        HistoryRecipes saved = historyRecipesRepository.save(historyRecipe);
        return historyRecipesMapper.toReadDTO(saved);
    }

    @Override
    public List<HistoryRecipesReadDTO> getAllHistoryRecipes() {
        List<HistoryRecipes> historyRecipes = historyRecipesRepository.findAll();
        return historyRecipesMapper.toReadDtoList(historyRecipes);
    }

    @Override
    public Optional<HistoryRecipesReadDTO> getHistoryRecipeById(Long id) {
        return historyRecipesRepository.findById(id)
                .map(historyRecipesMapper::toReadDTO);
    }

    @Override
    public HistoryRecipesReadDTO updateHistoryRecipe(Long id, HistoryRecipesNewUpdateDTO historyRecipeDTO) {
        HistoryRecipes historyRecipe = historyRecipesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("HistoryRecipe not found with id: " + id));

        historyRecipesMapper.updateEntity(historyRecipeDTO, historyRecipe);
        HistoryRecipes updated = historyRecipesRepository.save(historyRecipe);
        return historyRecipesMapper.toReadDTO(updated);
    }

    @Override
    public void deleteHistoryRecipe(Long id) {
        if (!historyRecipesRepository.existsById(id)) {
            throw new RuntimeException("HistoryRecipe not found with id: " + id);
        }
        historyRecipesRepository.deleteById(id);
    }

    @Override
    public List<HistoryRecipesReadDTO> getActivePrescriptionsByPetId(Long petId) {
        LocalDate today = LocalDate.now();
        List<HistoryRecipes> activeRecipes = historyRecipesRepository.findActivePrescriptionsByPetId(petId, today);
        return historyRecipesMapper.toReadDtoList(activeRecipes);
    }

    @Override
    public List<HistoryRecipesReadDTO> getAllPrescriptionsByPetId(Long petId) {
        List<HistoryRecipes> allRecipes = historyRecipesRepository.findByPetId(petId);
        return historyRecipesMapper.toReadDtoList(allRecipes);
    }
}
