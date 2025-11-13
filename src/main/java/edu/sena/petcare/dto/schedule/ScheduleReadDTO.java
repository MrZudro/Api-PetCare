package edu.sena.petcare.dto.schedule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleReadDTO {
    private Long id;
    private Long employeeId;
    private String day;
    private LocalTime start_time;
    private LocalTime end_time;
}