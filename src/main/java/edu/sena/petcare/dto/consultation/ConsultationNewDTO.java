package edu.sena.petcare.dto.consultation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultationNewDTO {

    @NotNull(message = "El ID del empleado (Veterinario) es obligatorio")
    private Long employeeId;

    @NotNull(message = "El ID de la clínica es obligatorio")
    private Long veterinaryClinicId;

    @NotNull(message = "El ID de la mascota es obligatorio")
    private Long petId; 
    
    // Se registra automáticamente en el servicio, pero se permite pasar si se registra retrospectivamente.
    private LocalDateTime consultationDateTime; 

    @NotBlank(message = "Los síntomas son obligatorios")
    @Size(max = 1000, message = "Los síntomas no deben exceder 1000 caracteres")
    private String symptoms;

    @NotBlank(message = "El diagnóstico es obligatorio")
    @Size(max = 1000, message = "El diagnóstico no debe exceder 1000 caracteres")
    private String diagnosis;

    @NotBlank(message = "El tratamiento es obligatorio")
    @Size(max = 1000, message = "El tratamiento no debe exceder 1000 caracteres")
    private String treatment;

    @Size(max = 1000, message = "Las notas no deben exceder 1000 caracteres")
    private String notes;
}