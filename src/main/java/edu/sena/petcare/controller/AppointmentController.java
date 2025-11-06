package edu.sena.petcare.controller;

import edu.sena.petcare.dto.appointment.AppointmentNewUpdateDTO;
import edu.sena.petcare.dto.appointment.AppointmentReadDTO;
import edu.sena.petcare.services.appointment.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments") // Endpoint principal
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    /**
     * @Documented POST /appointments
     * Crea una nueva cita en el sistema.
     * @param dto DTO con los datos de la cita.
     * @return 201 CREATED con la cita creada.
     */
    @PostMapping
    public ResponseEntity<AppointmentReadDTO> createAppointment(@Valid @RequestBody AppointmentNewUpdateDTO dto) {
        AppointmentReadDTO createdAppointment = appointmentService.createAppointment(dto);
        return new ResponseEntity<>(createdAppointment, HttpStatus.CREATED);
    }

    /**
     * @Documented GET /appointments
     * Obtiene el listado de todas las citas (CRUD Read All).
     * @return 200 OK con la lista de citas.
     */
    @GetMapping
    public ResponseEntity<List<AppointmentReadDTO>> getAllAppointments() {
        List<AppointmentReadDTO> appointments = appointmentService.getAllAppointments();
        return ResponseEntity.ok(appointments);
    }

    /**
     * @Documented GET /appointments/{id}
     * Obtiene una cita específica por su ID (CRUD Read One).
     * @param id ID de la cita.
     * @return 200 OK con los detalles de la cita.
     */
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentReadDTO> getAppointmentById(@PathVariable Long id) {
        AppointmentReadDTO appointment = appointmentService.getAppointmentById(id);
        return ResponseEntity.ok(appointment);
    }

    /**
     * @Documented PUT /appointments/{id}
     * Actualiza una cita existente (CRUD Update).
     * @param id ID de la cita a actualizar.
     * @param dto DTO con los nuevos datos.
     * @return 200 OK con la cita actualizada.
     */
    @PutMapping("/{id}")
    public ResponseEntity<AppointmentReadDTO> updateAppointment(@PathVariable Long id, @Valid @RequestBody AppointmentNewUpdateDTO dto) {
        AppointmentReadDTO updatedAppointment = appointmentService.updateAppointment(id, dto);
        return ResponseEntity.ok(updatedAppointment);
    }

    /**
     * @Documented DELETE /appointments/{id}
     * Cancela lógicamente una cita (CRUD Delete/Cancel).
     * @param id ID de la cita a cancelar.
     * @return 204 NO CONTENT.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
}