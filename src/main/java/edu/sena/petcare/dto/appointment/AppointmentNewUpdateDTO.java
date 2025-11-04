package edu.sena.petcare.dto.appointment;

import edu.sena.petcare.models.enums.AppointmentStatus;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentNewUpdateDTO {

    @NotNull(message = "El ID del cliente es obligatorio")
    private Long customerId;

    @NotNull(message = "El ID de la clínica es obligatorio")
    private Long veterinaryClinicId;

    // El empleado es opcional al crear.
    private Long employeeId; 

    @NotNull(message = "La fecha y hora de la cita son obligatorias")
    @FutureOrPresent(message = "La cita debe ser en la fecha actual o futura")
    private LocalDateTime appointmentDateTime;

    @Size(max = 500, message = "La razón no debe exceder los 500 caracteres")
    private String reason;
    
    // El estado solo se incluye para la actualización (ej: CANCELLED, CONFIRMED)
    private AppointmentStatus status; 
}