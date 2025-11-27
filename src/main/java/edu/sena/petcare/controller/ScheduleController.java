package edu.sena.petcare.controller;

import edu.sena.petcare.dto.schedule.ScheduleNewUpdateDTO;
import edu.sena.petcare.dto.schedule.ScheduleReadDTO;
import edu.sena.petcare.services.schedule.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
@Tag(name = "Schedules", description = "Endpoints for managing employee schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    @Operation(summary = "Create a new schedule")
    public ResponseEntity<ScheduleReadDTO> createSchedule(@RequestBody ScheduleNewUpdateDTO dto) {
        return new ResponseEntity<>(scheduleService.crearHorario(dto), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all schedules")
    public ResponseEntity<List<ScheduleReadDTO>> getAllSchedules() {
        return ResponseEntity.ok(scheduleService.todosHorarios());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get schedule by ID")
    public ResponseEntity<ScheduleReadDTO> getScheduleById(@PathVariable Long id) {
        return ResponseEntity.ok(scheduleService.unHorarioEspecifico(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing schedule")
    public ResponseEntity<ScheduleReadDTO> updateSchedule(@PathVariable Long id,
            @RequestBody ScheduleNewUpdateDTO dto) {
        return ResponseEntity.ok(scheduleService.actualizarHorario(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a schedule")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        scheduleService.borrarHorario(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/employee/{employeeId}")
    @Operation(summary = "Get schedules by employee ID")
    public ResponseEntity<List<ScheduleReadDTO>> getSchedulesByEmployee(@PathVariable Long employeeId) {
        return ResponseEntity.ok(scheduleService.horariosDeEmpleado(employeeId));
    }

    @GetMapping("/employee/{employeeId}/day/{day}")
    @Operation(summary = "Get schedules by employee ID and day")
    public ResponseEntity<List<ScheduleReadDTO>> getSchedulesByEmployeeAndDay(@PathVariable Long employeeId,
            @PathVariable String day) {
        return ResponseEntity.ok(scheduleService.horariosDeEmpleadoPorDia(employeeId, day));
    }
}