package edu.sena.petcare.dto.vaccinationhistory;

import lombok.Data;
import java.time.LocalDate;

@Data
public class VaccinationHistoryReadDTO {
    private Long id;
    private LocalDate applicationDate;
    private LocalDate nextDueDate;
    private String lotNumber;
    private String observations;
    private String certificate;

    // Nested info
    private String vaccineName;
    private String petName;
    private String employeeName;
    private String veterinaryClinicName;
}
