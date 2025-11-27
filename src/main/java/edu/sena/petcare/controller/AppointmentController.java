package edu.sena.petcare.controller;

import edu.sena.petcare.dto.appointment.AppointmentNewUpdateDTO;
import edu.sena.petcare.dto.appointment.AppointmentReadDTO;
import edu.sena.petcare.services.appointment.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
@Tag(name = "Appointments", description = "Endpoints for managing appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    @Operation(summary = "Create a new appointment")
    public ResponseEntity<AppointmentReadDTO> createAppointment(@RequestBody AppointmentNewUpdateDTO dto) {
        return new ResponseEntity<>(appointmentService.createAppointment(dto), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all appointments (Admin only)")
    public ResponseEntity<List<AppointmentReadDTO>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get appointment by ID")
    public ResponseEntity<AppointmentReadDTO> getAppointmentById(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentService.getAppointmentById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing appointment")
    public ResponseEntity<AppointmentReadDTO> updateAppointment(@PathVariable Long id,
            @RequestBody AppointmentNewUpdateDTO dto) {
        return ResponseEntity.ok(appointmentService.updateAppointment(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Cancel an appointment (Logical delete)")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/customer/{customerId}")
    @Operation(summary = "Get appointments by customer ID")
    public ResponseEntity<List<AppointmentReadDTO>> getAppointmentsByCustomer(@PathVariable Long customerId) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByCustomer(customerId));
    }

    @GetMapping("/employee/{employeeId}")
    @Operation(summary = "Get appointments by employee ID")
    public ResponseEntity<List<AppointmentReadDTO>> getAppointmentsByEmployee(@PathVariable Long employeeId) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByEmployee(employeeId));
    }
}