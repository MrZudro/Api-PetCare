package edu.sena.petcare.dto.historyrecipes;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class HistoryRecipesNewUpdateDTO {

    @NotNull(message = "La fecha de prescripción no puede estar vacía")
    private LocalDate prescribedDate;

    @NotNull(message = "La fecha de validez no puede estar vacía")
    private LocalDate validUntil;

    private String dosage;

    private String frequency;

    private String notes;

    @NotNull(message = "ID de mascota no puede estar vacío")
    private Long idPet;

    @NotNull(message = "ID de receta no puede estar vacío")
    private Long idRecipe;

    @NotNull(message = "ID de empleado no puede estar vacío")
    private Long idEmployee;

    private Long idConsultation;
}
