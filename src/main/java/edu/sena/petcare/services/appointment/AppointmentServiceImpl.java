package edu.sena.petcare.services.appointment;

import edu.sena.petcare.dto.appointment.AppointmentNewUpdateDTO;
import edu.sena.petcare.dto.appointment.AppointmentReadDTO;
import edu.sena.petcare.exceptions.ResourceNotFoundException;
import edu.sena.petcare.mapper.AppointmentMapper;
import edu.sena.petcare.models.Appointment;
import edu.sena.petcare.models.enums.AppointmentStatus;
import edu.sena.petcare.repositories.AppointmentRepository;
import edu.sena.petcare.repositories.CustomerRepository;
import edu.sena.petcare.repositories.VeterinaryClinicRepository;
import edu.sena.petcare.repositories.EmployeeRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;
    
    // Repositorios de dependencias inyectados
    private final CustomerRepository customerRepository;
    private final VeterinaryClinicRepository clinicRepository;
    private final EmployeeRepository employeeRepository;
    
    private static final String NOT_FOUND_MSG = "Cita con ID %d no encontrada";

    @Override
    @Transactional
    public AppointmentReadDTO createAppointment(AppointmentNewUpdateDTO dto) {
        // 1. Mapear DTO a Entidad e iniciar estado PENDING
        Appointment appointment = appointmentMapper.toEntity(dto);

        // 2. Asignar Entidades relacionadas (Validación de existencia)
        appointment.setCustomer(customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente con ID " + dto.getCustomerId() + " no encontrado")));
        
        appointment.setVeterinaryClinic(clinicRepository.findById(dto.getVeterinaryClinicId())
                .orElseThrow(() -> new ResourceNotFoundException("Clínica con ID " + dto.getVeterinaryClinicId() + " no encontrada")));
        
        if (dto.getEmployeeId() != null) {
            appointment.setEmployee(employeeRepository.findById(dto.getEmployeeId())
                    .orElseThrow(() -> new ResourceNotFoundException("Empleado/Veterinario con ID " + dto.getEmployeeId() + " no encontrado")));
        } else {
            appointment.setEmployee(null); 
        }
        
        // 3. Guardar y Mapear a DTO de Lectura
        Appointment savedAppointment = appointmentRepository.save(appointment);
        return appointmentMapper.toReadDto(savedAppointment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AppointmentReadDTO> getAllAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointmentMapper.toReadDtoList(appointments);
    }

    @Override
    @Transactional(readOnly = true)
    public AppointmentReadDTO getAppointmentById(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(NOT_FOUND_MSG, id)));
        return appointmentMapper.toReadDto(appointment);
    }

    @Override
    @Transactional
    public AppointmentReadDTO updateAppointment(Long id, AppointmentNewUpdateDTO dto) {
        // 1. Buscar la entidad existente
        Appointment existingAppointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(NOT_FOUND_MSG, id)));

        // 2. Actualizar las relaciones ManyToOne si cambiaron los IDs
        
        // Cliente
        if (existingAppointment.getCustomer() == null || !existingAppointment.getCustomer().getId().equals(dto.getCustomerId())) {
             existingAppointment.setCustomer(customerRepository.findById(dto.getCustomerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Cliente con ID " + dto.getCustomerId() + " no encontrado")));
        }
        
        // Clínica
        if (existingAppointment.getVeterinaryClinic() == null || !existingAppointment.getVeterinaryClinic().getId().equals(dto.getVeterinaryClinicId())) {
             existingAppointment.setVeterinaryClinic(clinicRepository.findById(dto.getVeterinaryClinicId())
                    .orElseThrow(() -> new ResourceNotFoundException("Clínica con ID " + dto.getVeterinaryClinicId() + " no encontrada")));
        }
        
        // Empleado
        Long currentEmployeeId = existingAppointment.getEmployee() != null ? existingAppointment.getEmployee().getId() : null;
        if ((currentEmployeeId == null && dto.getEmployeeId() != null) || 
            (currentEmployeeId != null && !currentEmployeeId.equals(dto.getEmployeeId()))) {
            
            if (dto.getEmployeeId() != null) {
                existingAppointment.setEmployee(employeeRepository.findById(dto.getEmployeeId())
                    .orElseThrow(() -> new ResourceNotFoundException("Empleado con ID " + dto.getEmployeeId() + " no encontrado")));
            } else {
                existingAppointment.setEmployee(null);
            }
        }
        
        // 3. Mapear los campos simples (fecha, razón, estado) del DTO a la entidad existente
        appointmentMapper.updateEntity(dto, existingAppointment);
        
        // 4. Guardar y Mapear a DTO de Lectura
        Appointment updatedAppointment = appointmentRepository.save(existingAppointment);
        return appointmentMapper.toReadDto(updatedAppointment);
    }

    @Override
    @Transactional
    public void deleteAppointment(Long id) {
        // Eliminación lógica
        Appointment appointmentToCancel = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(NOT_FOUND_MSG, id)));

        appointmentToCancel.setStatus(AppointmentStatus.CANCELLED);
        appointmentRepository.save(appointmentToCancel);
    }
}