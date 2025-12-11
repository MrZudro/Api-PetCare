package edu.sena.petcare.dto.schedule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalTime;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleNewUpdateDTO {
    private Long employeeId;
    private String day; // Día de la semana
    private LocalDate scheduleDate; // Fecha específica del horario
    private LocalTime start_time;
    private LocalTime end_time;
    private Boolean isOvertime; // Indica si son horas extras
    private LocalDate periodStartDate; // Inicio del período quincenal
    private LocalDate periodEndDate; // Fin del período quincenal
}