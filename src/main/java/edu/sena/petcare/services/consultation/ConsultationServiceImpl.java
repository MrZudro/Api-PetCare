package edu.sena.petcare.services.consultation;

import edu.sena.petcare.dto.consultation.ConsultationNewDTO;
import edu.sena.petcare.dto.consultation.ConsultationReadDTO;
import edu.sena.petcare.exceptions.ResourceNotFoundException;
import edu.sena.petcare.mapper.ConsultationMapper;
import edu.sena.petcare.models.Consultation;

// Repositorios requeridos (Descomentados)
import edu.sena.petcare.repositories.ConsultationRepository;
import edu.sena.petcare.repositories.EmployeeRepository;
import edu.sena.petcare.repositories.VeterinaryClinicRepository;
import edu.sena.petcare.repositories.PetRepository;
import java.util.Objects;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultationServiceImpl implements ConsultationService {

    private final ConsultationRepository consultationRepository;
    private final ConsultationMapper consultationMapper;

    // Repositorios de dependencias inyectados
    private final EmployeeRepository employeeRepository;
    private final VeterinaryClinicRepository veterinaryClinicRepository;
    private final PetRepository petRepository;

    private static final String NOT_FOUND_MSG = "Consulta con ID %d no encontrada";

    /**
     * @Documented Crea un nuevo registro de consulta.
     *             Asigna entidades relacionadas y la fecha/hora si no se
     *             proporciona.
     * @param dto DTO de la consulta.
     * @return DTO de lectura de la consulta creada.
     */
    @Override
    @Transactional
    public ConsultationReadDTO createConsultation(ConsultationNewDTO dto) {
        // 1. Mapear DTO a Entidad
        Consultation consultation = consultationMapper.toEntity(dto);

        // 2. Asignar Entidades relacionadas (Validación de existencia)
        consultation.setEmployee(
                employeeRepository.findById(Objects.requireNonNull(dto.getEmployeeId(), "Employee ID cannot be null"))
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Empleado/Veterinario con ID " + dto.getEmployeeId() + " no encontrado")));

        consultation.setVeterinaryClinic(veterinaryClinicRepository
                .findById(Objects.requireNonNull(dto.getVeterinaryClinicId(), "Veterinary Clinic ID cannot be null"))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Clínica con ID " + dto.getVeterinaryClinicId() + " no encontrada")));

        consultation.setPet(petRepository.findById(Objects.requireNonNull(dto.getPetId(), "Pet ID cannot be null"))
                .orElseThrow(
                        () -> new ResourceNotFoundException("Mascota con ID " + dto.getPetId() + " no encontrada")));

        // 3. Asignar Fecha/Hora si no se proporciona (Registro en tiempo real)
        if (dto.getConsultationDateTime() == null) {
            consultation.setConsultationDateTime(LocalDateTime.now());
        } else {
            consultation.setConsultationDateTime(dto.getConsultationDateTime());
        }

        // 4. Guardar y Mapear a DTO de Lectura
        Consultation savedConsultation = consultationRepository.save(consultation);
        return consultationMapper.toDto(savedConsultation);
    }

    /**
     * @Documented Obtiene una consulta específica por ID.
     * @param id ID de la consulta.
     * @return DTO de lectura de la consulta.
     */
    @Override
    @Transactional(readOnly = true)
    public ConsultationReadDTO getConsultationById(Long id) {
        Consultation consultation = consultationRepository.findById(Objects.requireNonNull(id, "ID cannot be null"))
                .orElseThrow(() -> new ResourceNotFoundException(String.format(NOT_FOUND_MSG, id)));
        return consultationMapper.toDto(consultation);
    }

    /**
     * @Documented Obtiene todas las consultas registradas.
     * @return Lista de todas las consultas.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ConsultationReadDTO> getAllConsultations() {
        List<Consultation> consultations = consultationRepository.findAll();
        return consultationMapper.toDtoList(consultations);
    }

    /**
     * @Documented Obtiene el historial de consultas de una mascota.
     * @param petId ID de la mascota.
     * @return Lista de consultas de la mascota ordenadas por fecha descendente.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ConsultationReadDTO> getConsultationHistoryByPet(Long petId) {
        List<Consultation> consultations = consultationRepository.findByPetIdOrderByConsultationDateTimeDesc(petId);
        return consultationMapper.toDtoList(consultations);
    }
}