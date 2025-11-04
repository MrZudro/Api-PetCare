package edu.sena.petcare.dto.consultation;

import edu.sena.petcare.models.enums.ConsultationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultationReadDTO {

    private Long id;
    private String employeeName;
    private String veterinaryClinicName;
    private Long petId;
    private String petName;
    private LocalDateTime consultationDateTime;
    private String symptoms;
    private String diagnosis;
    private String treatment;
    private String notes;
    private ConsultationStatus status;
}