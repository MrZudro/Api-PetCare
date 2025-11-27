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
import java.util.Objects;

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
        private static final String NOT_FOUND_MESSAGE = "no encontrado";

        @Override
        @Transactional
        public AppointmentReadDTO createAppointment(AppointmentNewUpdateDTO dto) {
                if (dto == null) {
                        throw new IllegalArgumentException("dto cannot be null");
                }

                Long customerId = dto.getCustomerId();
                if (customerId == null) {
                        throw new IllegalArgumentException("customerId cannot be null");
                }

                Long clinicId = dto.getVeterinaryClinicId();
                if (clinicId == null) {
                        throw new IllegalArgumentException("veterinaryClinicId cannot be null");
                }

                // 1. Mapear DTO a Entidad e iniciar estado PENDING
                Appointment appointment = appointmentMapper.toEntity(dto);

                // 2. Asignar Entidades relacionadas (Validación de existencia)
                appointment.setCustomer(customerRepository.findById(customerId)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Cliente con ID " + customerId + NOT_FOUND_MESSAGE)));

                appointment.setVeterinaryClinic(clinicRepository.findById(clinicId)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Clínica con ID " + clinicId + " no encontrada")));

                Long employeeId = dto.getEmployeeId();
                if (employeeId != null) {
                        appointment.setEmployee(employeeRepository.findById(employeeId)
                                        .orElseThrow(() -> new ResourceNotFoundException(
                                                        "Empleado/Veterinario con ID " + employeeId
                                                                        + NOT_FOUND_MESSAGE)));
                } else {
                        appointment.setEmployee(null);
                }

                // 3. Guardar y Mapear a DTO de Lectura
                Appointment savedAppointment = appointmentRepository.save(appointment);
                return appointmentMapper.toDto(savedAppointment);
        }

        @Override
        @Transactional(readOnly = true)
        public List<AppointmentReadDTO> getAllAppointments() {
                List<Appointment> appointments = appointmentRepository.findAll();
                return appointmentMapper.toDtoList(appointments);
        }

        @Override
        @Transactional(readOnly = true)
        public AppointmentReadDTO getAppointmentById(Long id) {
                if (id == null) {
                        throw new IllegalArgumentException("id cannot be null");
                }
                Appointment appointment = appointmentRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException(String.format(NOT_FOUND_MSG, id)));
                return appointmentMapper.toDto(appointment);
        }

        @Override
        @Transactional
        public AppointmentReadDTO updateAppointment(Long id, AppointmentNewUpdateDTO dto) {
                if (id == null) {
                        throw new IllegalArgumentException("id cannot be null");
                }
                if (dto == null) {
                        throw new IllegalArgumentException("dto cannot be null");
                }
                // 1. Buscar la entidad existente
                Appointment existingAppointment = appointmentRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException(String.format(NOT_FOUND_MSG, id)));

                // 2. Actualizar las relaciones ManyToOne si cambiaron los IDs
                updateCustomer(dto, existingAppointment);
                updateClinic(dto, existingAppointment);
                updateEmployee(dto, existingAppointment);

                // 3. Mapear los campos simples (fecha, razón, estado) del DTO a la entidad
                // existente
                appointmentMapper.updateEntity(dto, existingAppointment);

                // 4. Guardar y Mapear a DTO de Lectura
                Appointment updatedAppointment = appointmentRepository.save(existingAppointment);
                return appointmentMapper.toDto(updatedAppointment);
        }

        private void updateCustomer(AppointmentNewUpdateDTO dto, Appointment existingAppointment) {
                if (dto.getCustomerId() != null && (existingAppointment.getCustomer() == null
                                || !existingAppointment.getCustomer().getId().equals(dto.getCustomerId()))) {
                        existingAppointment.setCustomer(customerRepository
                                        .findById(Objects.requireNonNull(dto.getCustomerId(),
                                                        "Customer ID cannot be null"))
                                        .orElseThrow(() -> new ResourceNotFoundException(
                                                        "Cliente con ID " + dto.getCustomerId() + NOT_FOUND_MESSAGE)));
                }
        }

        private void updateClinic(AppointmentNewUpdateDTO dto, Appointment existingAppointment) {
                if (dto.getVeterinaryClinicId() != null && (existingAppointment.getVeterinaryClinic() == null
                                || !existingAppointment.getVeterinaryClinic().getId()
                                                .equals(dto.getVeterinaryClinicId()))) {
                        existingAppointment.setVeterinaryClinic(clinicRepository
                                        .findById(Objects.requireNonNull(dto.getVeterinaryClinicId(),
                                                        "Clinic ID cannot be null"))
                                        .orElseThrow(() -> new ResourceNotFoundException(
                                                        "Clínica con ID " + dto.getVeterinaryClinicId()
                                                                        + " no encontrada")));
                }
        }

        private void updateEmployee(AppointmentNewUpdateDTO dto, Appointment existingAppointment) {
                Long currentEmployeeId = existingAppointment.getEmployee() != null
                                ? existingAppointment.getEmployee().getId()
                                : null;
                if ((currentEmployeeId == null && dto.getEmployeeId() != null) ||
                                (currentEmployeeId != null && !currentEmployeeId.equals(dto.getEmployeeId()))) {

                        if (dto.getEmployeeId() != null) {
                                existingAppointment.setEmployee(employeeRepository
                                                .findById(Objects.requireNonNull(dto.getEmployeeId(),
                                                                "Employee ID cannot be null"))
                                                .orElseThrow(() -> new ResourceNotFoundException(
                                                                "Empleado con ID " + dto.getEmployeeId()
                                                                                + NOT_FOUND_MESSAGE)));
                        } else {
                                existingAppointment.setEmployee(null);
                        }
                }
        }

        @Override
        @Transactional
        public void deleteAppointment(Long id) {
                if (id == null) {
                        throw new IllegalArgumentException("id cannot be null");
                }
                // Eliminación lógica
                Appointment appointmentToCancel = appointmentRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException(String.format(NOT_FOUND_MSG, id)));

                appointmentToCancel.setStatus(AppointmentStatus.CANCELLED);
                appointmentRepository.save(appointmentToCancel);
        }

        @Override
        @Transactional(readOnly = true)
        public List<AppointmentReadDTO> getAppointmentsByCustomer(Long customerId) {
                if (customerId == null) {
                        throw new IllegalArgumentException("customerId cannot be null");
                }
                List<Appointment> appointments = appointmentRepository.findByCustomerId(customerId);
                return appointmentMapper.toDtoList(appointments);
        }

        @Override
        @Transactional(readOnly = true)
        public List<AppointmentReadDTO> getAppointmentsByEmployee(Long employeeId) {
                if (employeeId == null) {
                        throw new IllegalArgumentException("employeeId cannot be null");
                }
                List<Appointment> appointments = appointmentRepository.findByEmployeeId(employeeId);
                return appointmentMapper.toDtoList(appointments);
        }
}
