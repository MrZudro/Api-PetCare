package edu.sena.petcare.services.appointment;

import edu.sena.petcare.dto.appointment.AppointmentNewUpdateDTO;
import edu.sena.petcare.dto.appointment.AppointmentReadDTO;

import java.util.List;

public interface AppointmentService {

    /**
     * @Documented Crea una nueva cita con estado PENDING.
     * @param dto DTO de la cita.
     * @return DTO de lectura de la cita creada.
     */
    AppointmentReadDTO createAppointment(AppointmentNewUpdateDTO dto);

    /**
     * @Documented Obtiene todas las citas. (Solo para administradores)
     * @return Lista de todas las citas.
     */
    List<AppointmentReadDTO> getAllAppointments();

    /**
     * @Documented Obtiene una cita específica por ID.
     * @param id ID de la cita.
     * @return DTO de lectura de la cita.
     */
    AppointmentReadDTO getAppointmentById(Long id);

    /**
     * @Documented Actualiza una cita existente, permitiendo cambios en fecha, hora,
     *             razón o estado.
     * @param id  ID de la cita a actualizar.
     * @param dto DTO con los datos actualizados.
     * @return DTO de lectura de la cita actualizada.
     */
    AppointmentReadDTO updateAppointment(Long id, AppointmentNewUpdateDTO dto);

    /**
     * @Documented Elimina lógicamente una cita marcándola como CANCELLED.
     * @param id ID de la cita a eliminar.
     */
    void deleteAppointment(Long id);

    List<AppointmentReadDTO> getAppointmentsByCustomer(Long customerId);

    List<AppointmentReadDTO> getAppointmentsByEmployee(Long employeeId);
}