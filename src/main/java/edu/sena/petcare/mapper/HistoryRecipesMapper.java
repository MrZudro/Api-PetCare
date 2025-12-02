package edu.sena.petcare.mapper;

import org.springframework.stereotype.Component;
import edu.sena.petcare.dto.historyrecipes.HistoryRecipesNewUpdateDTO;
import edu.sena.petcare.dto.historyrecipes.HistoryRecipesReadDTO;
import edu.sena.petcare.models.HistoryRecipes;
import edu.sena.petcare.models.Pet;
import edu.sena.petcare.models.Recipe;
import edu.sena.petcare.models.Employee;
import edu.sena.petcare.models.Consultation;
import edu.sena.petcare.repositories.PetRepository;
import edu.sena.petcare.repositories.RecipeRepository;
import edu.sena.petcare.repositories.EmployeeRepository;
import edu.sena.petcare.repositories.ConsultationRepository;
import edu.sena.petcare.utility.ListaMappeo;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Component
@RequiredArgsConstructor
public class HistoryRecipesMapper {

    private final PetRepository petRepository;
    private final RecipeRepository recipeRepository;
    private final EmployeeRepository employeeRepository;
    private final ConsultationRepository consultationRepository;

    public HistoryRecipesReadDTO toReadDTO(HistoryRecipes history) {
        if (history == null) {
            return null;
        }

        HistoryRecipesReadDTO dto = new HistoryRecipesReadDTO();
        dto.setId(history.getId());
        dto.setPrescribedDate(history.getPrescribedDate());
        dto.setValidUntil(history.getValidUntil());
        dto.setDosage(history.getDosage());
        dto.setFrequency(history.getFrequency());
        dto.setNotes(history.getNotes());

        // Map nested data
        if (history.getPet() != null) {
            dto.setPetName(history.getPet().getName());
        }
        if (history.getRecipe() != null) {
            dto.setMedicationName(history.getRecipe().getMedicationName());
        }
        if (history.getEmployee() != null) {
            // Employee extends User, so getName() is inherited
            dto.setEmployeeName(history.getEmployee().getNames());
        }
        if (history.getConsultation() != null) {
            dto.setConsultationId(history.getConsultation().getId());
        }

        return dto;
    }

    public HistoryRecipes toEntity(HistoryRecipesNewUpdateDTO dto) {
        if (dto == null) {
            return null;
        }

        HistoryRecipes history = new HistoryRecipes();
        history.setPrescribedDate(dto.getPrescribedDate());
        history.setValidUntil(dto.getValidUntil());
        history.setDosage(dto.getDosage());
        history.setFrequency(dto.getFrequency());
        history.setNotes(dto.getNotes());

        // Map relationships
        if (dto.getIdPet() != null) {
            Pet pet = petRepository.findById(dto.getIdPet())
                    .orElseThrow(() -> new RuntimeException("Pet not found with id: " + dto.getIdPet()));
            history.setPet(pet);
        }

        if (dto.getIdRecipe() != null) {
            Recipe recipe = recipeRepository.findById(dto.getIdRecipe())
                    .orElseThrow(() -> new RuntimeException("Recipe not found with id: " + dto.getIdRecipe()));
            history.setRecipe(recipe);
        }

        if (dto.getIdEmployee() != null) {
            Employee employee = employeeRepository.findById(dto.getIdEmployee())
                    .orElseThrow(() -> new RuntimeException("Employee not found with id: " + dto.getIdEmployee()));
            history.setEmployee(employee);
        }

        if (dto.getIdConsultation() != null) {
            Consultation consultation = consultationRepository.findById(dto.getIdConsultation())
                    .orElse(null);
            history.setConsultation(consultation);
        }

        return history;
    }

    public void updateEntity(HistoryRecipesNewUpdateDTO dto, HistoryRecipes history) {
        if (dto == null || history == null) {
            return;
        }

        if (dto.getPrescribedDate() != null)
            history.setPrescribedDate(dto.getPrescribedDate());
        if (dto.getValidUntil() != null)
            history.setValidUntil(dto.getValidUntil());
        if (dto.getDosage() != null)
            history.setDosage(dto.getDosage());
        if (dto.getFrequency() != null)
            history.setFrequency(dto.getFrequency());
        if (dto.getNotes() != null)
            history.setNotes(dto.getNotes());

        // Update relationships
        if (dto.getIdPet() != null) {
            Pet pet = petRepository.findById(dto.getIdPet())
                    .orElseThrow(() -> new RuntimeException("Pet not found with id: " + dto.getIdPet()));
            history.setPet(pet);
        }

        if (dto.getIdRecipe() != null) {
            Recipe recipe = recipeRepository.findById(dto.getIdRecipe())
                    .orElseThrow(() -> new RuntimeException("Recipe not found with id: " + dto.getIdRecipe()));
            history.setRecipe(recipe);
        }

        if (dto.getIdEmployee() != null) {
            Employee employee = employeeRepository.findById(dto.getIdEmployee())
                    .orElseThrow(() -> new RuntimeException("Employee not found with id: " + dto.getIdEmployee()));
            history.setEmployee(employee);
        }

        if (dto.getIdConsultation() != null) {
            Consultation consultation = consultationRepository.findById(dto.getIdConsultation())
                    .orElse(null);
            history.setConsultation(consultation);
        }
    }

    public List<HistoryRecipesReadDTO> toReadDtoList(List<HistoryRecipes> entities) {
        return ListaMappeo.toDtoList(entities, this::toReadDTO);
    }
}
