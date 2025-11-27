package edu.sena.petcare.dto.appointment;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AppointmentDTO {
    @NotNull
    private Long customerId;

    @NotNull
    private Long veterinaryClinicId;

    private Long employeeId; // Optional initially? User says "elige con que profesional", so likely
                             // required.

    @NotNull
    private LocalDateTime appointmentDateTime;

    private String reason;
}
