package edu.sena.petcare.dto.historyrecipes;

import lombok.Data;

import java.time.LocalDate;

@Data
public class HistoryRecipesReadDTO {
    private Long id;
    private LocalDate prescribedDate;
    private LocalDate validUntil;
    private String dosage;
    private String frequency;
    private String notes;

    // Nested data
    private String petName;
    private String medicationName;
    private String employeeName;
    private Long consultationId;
}
