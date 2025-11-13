package edu.sena.petcare.services.schedule;

import edu.sena.petcare.dto.schedule.ScheduleReadDTO;
import edu.sena.petcare.dto.schedule.ScheduleNewUpdateDTO;

import java.util.List;

public interface ScheduleService {

    ScheduleReadDTO crearHorario(ScheduleNewUpdateDTO nuevoHorarioDTO);

    List<ScheduleReadDTO> todosHorarios();

    ScheduleReadDTO unHorarioEspecifico(Long id);

    List<ScheduleReadDTO> horariosDeEmpleado(Long employeeId);

    List<ScheduleReadDTO> horariosDeEmpleadoPorDia(Long employeeId, String day);

    ScheduleReadDTO actualizarHorario(Long id, ScheduleNewUpdateDTO horarioActualizadoDTO);

    void borrarHorario(Long id);
}