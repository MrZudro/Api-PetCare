package edu.sena.petcare.services.examdetail;


import edu.sena.petcare.dto.examdetail.ExamDetailNewUpdateDTO;
import edu.sena.petcare.dto.examdetail.ExamDetailReadDTO;
import edu.sena.petcare.exceptions.ResourceNotFoundException;
import edu.sena.petcare.mapper.ExamDetailMapper;
import edu.sena.petcare.models.*;
import edu.sena.petcare.repositories.*; // Asegúrate de tener los repositorios
import edu.sena.petcare.services.ExamDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExamDetailServiceImpl implements ExamDetailService {

    private final ExamDetailRepository examDetailRepository;
    private final ExamRepository examRepository; // Repositorio del tipo de Examen
    private final PetRepository petRepository;   // Repositorio de la Mascota
    private final EmployeeRepository veterinarianRepository; // Repositorio del Veterinario
    private final ExamDetailMapper examDetailMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ExamDetailReadDTO> findAll() {
        return examDetailRepository.findAll().stream()
                .map(examDetailMapper::toReadDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ExamDetailReadDTO findById(Long id) {
        ExamDetail detail = examDetailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Detalle de Examen no encontrado con id: " + id));
        return examDetailMapper.toReadDTO(detail);
    }

    @Override
    @Transactional
    public ExamDetailReadDTO create(ExamDetailNewUpdateDTO dto) {
        // 1. Buscar y validar existencia de las entidades relacionadas
        Exam exam = examRepository.findById(dto.getExamId())
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de Examen no encontrado con id: " + dto.getExamId()));

        Pet pet = petRepository.findById(dto.getPetId())
                .orElseThrow(() -> new ResourceNotFoundException("Mascota no encontrada con id: " + dto.getPetId()));

        Employee employee = employeeRepository.findByIdAndStatus(dto.getEmployeeId(), EmployeeStatus.ACTIVE)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado (Veterinario) no encontrado o inactivo con id: " + dto.getEmployeeId()));

       // 2. Crear la entidad ExamDetail y asignar las referencias
        ExamDetail detail = new ExamDetail();
        detail.setExam(exam);
        detail.setPet(pet);
        detail.serEmployee(employee);
        detail.setResults(dto.getResults());

        // Asignar fecha: si no viene en el DTO, usar la fecha y hora actual
        detail.setExamDate(dto.getExamDate() != null ? dto.getExamDate() : LocalDateTime.now());

        // 3. Guardar
        ExamDetail savedDetail = examDetailRepository.save(detail);
        return examDetailMapper.toReadDTO(savedDetail);
    }

    @Override
    @Transactional
    public ExamDetailReadDTO update(Long id, ExamDetailNewUpdateDTO dto) {
        // 1. Buscar el detalle de examen existente
        ExamDetail existingDetail = examDetailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Detalle de Examen no encontrado con id: " + id));

        // 2. Las referencias (Exam, Pet, Vet) NO se deben cambiar en una actualización
        //    Si el DTO trae IDs diferentes, se deben revalidar o ignorar, lo más seguro es ignorarlos
        //    y sólo permitir actualizar 'results' y 'examDate'.

        // 3. Mapear campos actualizables (results, examDate)
        examDetailMapper.updateEntityFromDto(dto, existingDetail);

        // 4. Guardar
        ExamDetail updatedDetail = examDetailRepository.save(existingDetail);
        return examDetailMapper.toReadDTO(updatedDetail);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!examDetailRepository.existsById(id)) {
            throw new ResourceNotFoundException("Detalle de Examen no encontrado con id: " + id);
        }
        examDetailRepository.deleteById(id);
    }
}