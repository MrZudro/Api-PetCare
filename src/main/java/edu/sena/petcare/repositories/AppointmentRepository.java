package edu.sena.petcare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.sena.petcare.models.Appointment;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    
    /**
     * @Documented Busca citas por un cliente específico. Útil para el historial.
     * @param customerId ID del cliente.
     * @return Lista de citas del cliente.
     */
    List<Appointment> findByCustomerId(Long customerId);

    /**
     * @Documented Busca citas en un rango de fechas/horas para verificar disponibilidad de un empleado.
     * @param employeeId ID del empleado (Veterinario).
     * @param start Rango de inicio.
     * @param end Rango final.
     * @return Lista de citas que coinciden.
     */
    List<Appointment> findByEmployeeIdAndAppointmentDateTimeBetween(Long employeeId, LocalDateTime start, LocalDateTime end);
}