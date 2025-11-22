package edu.sena.petcare.controller;

import edu.sena.petcare.dto.schedule.ScheduleReadDTO;
import edu.sena.petcare.dto.schedule.ScheduleNewUpdateDTO;
import edu.sena.petcare.services.schedule.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleReadDTO> createSchedule(@Valid @RequestBody ScheduleNewUpdateDTO scheduleDTO) {
        ScheduleReadDTO created = scheduleService.crearHorario(scheduleDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleReadDTO>> getAllSchedules() {
        return ResponseEntity.ok(scheduleService.todosHorarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleReadDTO> getScheduleById(@PathVariable Long id) {
        return ResponseEntity.ok(scheduleService.unHorarioEspecifico(id));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<ScheduleReadDTO>> getSchedulesByEmployee(@PathVariable Long employeeId) {
        return ResponseEntity.ok(scheduleService.horariosDeEmpleado(employeeId));
    }

    @GetMapping("/employee/{employeeId}/day/{day}")
    public ResponseEntity<List<ScheduleReadDTO>> getSchedulesByEmployeeAndDay(@PathVariable Long employeeId, @PathVariable String day) {
        return ResponseEntity.ok(scheduleService.horariosDeEmpleadoPorDia(employeeId, day));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleReadDTO> updateSchedule(@PathVariable Long id, @Valid @RequestBody ScheduleNewUpdateDTO scheduleDTO) {
        return ResponseEntity.ok(scheduleService.actualizarHorario(id, scheduleDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        scheduleService.borrarHorario(id);
        return ResponseEntity.noContent().build();
    }
}