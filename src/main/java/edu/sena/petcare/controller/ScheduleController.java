package edu.sena.petcare.controller;

import edu.sena.petcare.dto.schedule.ScheduleNewUpdateDTO;
import edu.sena.petcare.dto.schedule.ScheduleReadDTO;
import edu.sena.petcare.services.schedule.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
@Tag(name = "Schedules", description = "Endpoints para gestión de horarios de empleados")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    @Operation(summary = "Crear un nuevo horario")
    public ResponseEntity<ScheduleReadDTO> createSchedule(@RequestBody ScheduleNewUpdateDTO dto) {
        return new ResponseEntity<>(scheduleService.crearHorario(dto), HttpStatus.CREATED);
    }

    @PostMapping("/bulk")
    @Operation(summary = "Crear múltiples horarios en lote")
    public ResponseEntity<List<ScheduleReadDTO>> createBulkSchedules(@RequestBody List<ScheduleNewUpdateDTO> dtos) {
        return new ResponseEntity<>(scheduleService.crearHorariosEnLote(dtos), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Obtener todos los horarios")
    public ResponseEntity<List<ScheduleReadDTO>> getAllSchedules() {
        return ResponseEntity.ok(scheduleService.todosHorarios());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener horario por ID")
    public ResponseEntity<ScheduleReadDTO> getScheduleById(@PathVariable Long id) {
        return ResponseEntity.ok(scheduleService.unHorarioEspecifico(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un horario existente")
    public ResponseEntity<ScheduleReadDTO> updateSchedule(@PathVariable Long id,
            @RequestBody ScheduleNewUpdateDTO dto) {
        return ResponseEntity.ok(scheduleService.actualizarHorario(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un horario")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        scheduleService.borrarHorario(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/employee/{employeeId}")
    @Operation(summary = "Obtener horarios por ID de empleado")
    public ResponseEntity<List<ScheduleReadDTO>> getSchedulesByEmployee(@PathVariable Long employeeId) {
        return ResponseEntity.ok(scheduleService.horariosDeEmpleado(employeeId));
    }

    @GetMapping("/employee/{employeeId}/day/{day}")
    @Operation(summary = "Obtener horarios por ID de empleado y día")
    public ResponseEntity<List<ScheduleReadDTO>> getSchedulesByEmployeeAndDay(@PathVariable Long employeeId,
            @PathVariable String day) {
        return ResponseEntity.ok(scheduleService.horariosDeEmpleadoPorDia(employeeId, day));
    }

    @GetMapping("/employee/{employeeId}/upcoming")
    @Operation(summary = "Obtener horarios próximos de un empleado")
    public ResponseEntity<List<ScheduleReadDTO>> getUpcomingSchedules(
            @PathVariable Long employeeId,
            @RequestParam(defaultValue = "15") int days) {
        return ResponseEntity.ok(scheduleService.obtenerHorariosProximos(employeeId, days));
    }

    @GetMapping("/employee/{employeeId}/period")
    @Operation(summary = "Obtener horarios de un empleado por período de fechas")
    public ResponseEntity<List<ScheduleReadDTO>> getSchedulesByEmployeeAndPeriod(
            @PathVariable Long employeeId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return ResponseEntity.ok(scheduleService.obtenerHorariosPorPeriodo(employeeId, start, end));
    }

    @GetMapping("/calendar")
    @Operation(summary = "Obtener todos los horarios de todos los empleados por período (para vista de calendario)")
    public ResponseEntity<List<ScheduleReadDTO>> getCalendarSchedules(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return ResponseEntity.ok(scheduleService.obtenerTodosHorariosPorPeriodo(start, end));
    }
}