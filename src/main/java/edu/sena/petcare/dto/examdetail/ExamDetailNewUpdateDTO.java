package edu.sena.petcare.dto.examdetail;


import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ExamDetailNewUpdateDTO {

    // La fecha puede ser nula, o la API la pone al crear/actualizar si es nula
    private LocalDateTime examDate;

    private String results;

    @NotNull(message = "El ID del tipo de examen es obligatorio.")
    private Long examId;

    @NotNull(message = "El ID de la mascota es obligatorio.")
    private Long petId;

    @NotNull(message = "El ID del veterinario es obligatorio.")
    private Long employeeId;
}