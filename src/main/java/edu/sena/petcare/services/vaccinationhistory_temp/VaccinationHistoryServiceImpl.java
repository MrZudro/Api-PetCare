package edu.sena.petcare.services.vaccinationhistory_temp;

import edu.sena.petcare.dto.VaccinationHistory.VaccinationHistoryCreateDTO;
import edu.sena.petcare.dto.VaccinationHistory.VaccinationHistoryReadDTO;
import edu.sena.petcare.dto.VaccinationHistory.VaccinationHistoryUpdateDTO;
import edu.sena.petcare.exceptions.ResourceNotFoundException;
import edu.sena.petcare.mapper.VaccinationHistoryMapper;
import edu.sena.petcare.models.VaccinationHistory;
import edu.sena.petcare.repositories.VaccinationHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaccinationHistoryServiceImpl implements VaccinationHistoryService {

    private final VaccinationHistoryRepository repository;
    private final VaccinationHistoryMapper mapper;

    public VaccinationHistoryServiceImpl(VaccinationHistoryRepository repository, VaccinationHistoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public VaccinationHistoryReadDTO create(VaccinationHistoryCreateDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("dto cannot be null");
        }
        VaccinationHistory vaccine = mapper.toEntity(dto);
        @SuppressWarnings("null")
        VaccinationHistory savedVaccine = repository.save(vaccine);
        return mapper.toDto(savedVaccine);
    }

    @Override
    public VaccinationHistoryReadDTO update(Long id, VaccinationHistoryUpdateDTO dto) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        if (dto == null) {
            throw new IllegalArgumentException("dto cannot be null");
        }
        VaccinationHistory vaccine = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vacuna no encontrada"));
        mapper.updateEntity(dto, vaccine);
        @SuppressWarnings("null")
        VaccinationHistory savedVaccine = repository.save(vaccine);
        return mapper.toDto(savedVaccine);
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Vacuna no encontrada para eliminar");
        }
        repository.deleteById(id);
    }

    @Override
    public VaccinationHistoryReadDTO getById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Vacuna no encontrada"));
    }

    @Override
    public List<VaccinationHistoryReadDTO> getAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }
}
