package edu.sena.petcare.dto.appointment;

import edu.sena.petcare.models.enums.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentReadDTO {

    private Long id;
    private Long customerId;
    private String customerName; // Nombre del cliente
    private Long veterinaryClinicId; // ID de la clínica
    private String veterinaryClinicName; // Nombre de la clínica
    private Long employeeId; // ID del empleado
    private String employeeName; // Nombre del empleado/veterinario asignado
    private Long serviceId; // ID del servicio
    private String serviceName; // Nombre del servicio
    private LocalDateTime appointmentDateTime;
    private AppointmentStatus status;
    private String reason;
}