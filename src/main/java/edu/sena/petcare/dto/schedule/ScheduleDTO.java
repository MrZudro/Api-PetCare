package edu.sena.petcare.dto.schedule;

import java.time.LocalTime;
import lombok.Data;

@Data
public class ScheduleDTO {
    private Long id;
    private String day;
    private LocalTime startTime;
    private LocalTime endTime;
    private Long employeeId;
}
