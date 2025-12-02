package edu.sena.petcare.dto.vaccinationhistory;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class VaccinationHistoryNewUpdateDTO {

    @NotNull(message = "Application date is required")
    private LocalDate applicationDate;

    private LocalDate nextDueDate;

    private String lotNumber;

    private String observations;

    private String certificate;

    @NotNull(message = "Vaccine ID is required")
    private Long idVaccine;

    @NotNull(message = "Pet ID is required")
    private Long idPet;

    @NotNull(message = "Employee ID is required")
    private Long idEmployee;

    @NotNull(message = "Veterinary Clinic ID is required")
    private Long idVeterinaryClinic;
}
