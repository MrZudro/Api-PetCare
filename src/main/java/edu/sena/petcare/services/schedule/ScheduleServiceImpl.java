package edu.sena.petcare.services.schedule;

import edu.sena.petcare.dto.schedule.ScheduleReadDTO;
import edu.sena.petcare.dto.schedule.ScheduleNewUpdateDTO;
import edu.sena.petcare.exceptions.BadRequestException;
import edu.sena.petcare.exceptions.ResourceNotFoundException;
import edu.sena.petcare.mapper.ScheduleMapper;
import edu.sena.petcare.models.Employee;
import edu.sena.petcare.models.Schedule;
import edu.sena.petcare.repositories.ScheduleRepository;
import edu.sena.petcare.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final EmployeeRepository employeeRepository;
    private final ScheduleMapper scheduleMapper;

    @Override
    public ScheduleReadDTO crearHorario(ScheduleNewUpdateDTO nuevoHorarioDTO) {
        validarDTO(nuevoHorarioDTO);

        Employee empleado = employeeRepository.findById(nuevoHorarioDTO.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Empleado con id " + nuevoHorarioDTO.getEmployeeId() + " no encontrado"));

        validarRangoHoras(nuevoHorarioDTO.getStart_time(), nuevoHorarioDTO.getEnd_time());
        validarSolapamiento(nuevoHorarioDTO.getEmployeeId(), nuevoHorarioDTO.getDay(),
                nuevoHorarioDTO.getStart_time(), nuevoHorarioDTO.getEnd_time(), null);

        Schedule entity = scheduleMapper.toEntity(nuevoHorarioDTO);
        entity.setEmployee(empleado);

        Schedule saved = scheduleRepository.save(entity);
        return scheduleMapper.toDto(saved);
    }

    @Override
    public List<ScheduleReadDTO> todosHorarios() {
        return scheduleRepository.findAll()
                .stream()
                .map(scheduleMapper::toDto)
                .toList();
    }

    @Override
    public ScheduleReadDTO unHorarioEspecifico(Long id) {
        Schedule s = scheduleRepository.findById(Objects.requireNonNull(id, "id es obligatorio"))
                .orElseThrow(() -> new ResourceNotFoundException("Horario con id " + id + " no encontrado"));
        return scheduleMapper.toDto(s);
    }

    @Override
    public List<ScheduleReadDTO> horariosDeEmpleado(Long employeeId) {
        Objects.requireNonNull(employeeId, "employeeId es obligatorio");
        return scheduleRepository.findByEmployee_Id(employeeId)
                .stream()
                .map(scheduleMapper::toDto)
                .toList();
    }

    @Override
    public List<ScheduleReadDTO> horariosDeEmpleadoPorDia(Long employeeId, String day) {
        Objects.requireNonNull(employeeId, "employeeId es obligatorio");
        Objects.requireNonNull(day, "day es obligatorio");
        return scheduleRepository.findByEmployee_IdAndDay(employeeId, day)
                .stream()
                .map(scheduleMapper::toDto)
                .toList();
    }

    @Override
    public ScheduleReadDTO actualizarHorario(Long id, ScheduleNewUpdateDTO horarioActualizadoDTO) {
        Objects.requireNonNull(id, "id es obligatorio");
        validarDTO(horarioActualizadoDTO);

        Schedule toUpdate = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Horario con id " + id + " no encontrado"));

        Employee empleado = employeeRepository.findById(horarioActualizadoDTO.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Empleado con id " + horarioActualizadoDTO.getEmployeeId() + " no encontrado"));

        validarRangoHoras(horarioActualizadoDTO.getStart_time(), horarioActualizadoDTO.getEnd_time());
        validarSolapamiento(horarioActualizadoDTO.getEmployeeId(), horarioActualizadoDTO.getDay(),
                horarioActualizadoDTO.getStart_time(), horarioActualizadoDTO.getEnd_time(), id);

        scheduleMapper.updateEntity(horarioActualizadoDTO, toUpdate);
        toUpdate.setEmployee(empleado);

        Schedule updated = scheduleRepository.save(toUpdate);
        return scheduleMapper.toDto(updated);
    }

    @Override
    public void borrarHorario(Long id) {
        if (!scheduleRepository.existsById(Objects.requireNonNull(id, "id es obligatorio"))) {
            throw new ResourceNotFoundException("Horario con id " + id + " no encontrado");
        }
        scheduleRepository.deleteById(id);
    }

    private void validarDTO(ScheduleNewUpdateDTO dto) {
        if (dto.getEmployeeId() == null) throw new BadRequestException("employeeId es obligatorio");
        if (dto.getDay() == null || dto.getDay().isBlank()) throw new BadRequestException("day es obligatorio");
        if (dto.getStart_time() == null || dto.getEnd_time() == null) throw new BadRequestException("start_time y end_time son obligatorios");
    }

    private void validarRangoHoras(LocalTime start, LocalTime end) {
        if (!start.isBefore(end)) {
            throw new BadRequestException("start_time debe ser anterior a end_time");
        }
    }

    private void validarSolapamiento(Long employeeId, String day, LocalTime start, LocalTime end, Long excludeId) {
        var overlapping = scheduleRepository.findOverlapping(employeeId, day, start, end);
        boolean exists = overlapping.stream().anyMatch(s -> excludeId == null || !s.getId().equals(excludeId));
        if (exists) {
            throw new BadRequestException("El horario se solapa con otro existente para el mismo empleado y día");
        }
    }
}