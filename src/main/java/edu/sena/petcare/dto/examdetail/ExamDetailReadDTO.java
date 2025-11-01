package edu.sena.petcare.dto.examdetail;


import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ExamDetailReadDTO {

    private Long id;
    private LocalDateTime examDate;
    private String results;

    // Relaciones
    private Long examId;
    private Long petId;
    private Long veterinarianId;
}