package edu.sena.petcare.services.VaccinationHistory;

import edu.sena.petcare.dto.VaccinationHistory.VaccinationHistoryCreateDTO;
import edu.sena.petcare.dto.VaccinationHistory.VaccinationHistoryReadDTO;
import edu.sena.petcare.dto.VaccinationHistory.VaccinationHistoryUpdateDTO;
import edu.sena.petcare.mapper.VaccinationHistoryMapper;
import edu.sena.petcare.models.VaccinationHistory;
import edu.sena.petcare.repositories.VaccinationHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        return mapper.toDto(repository.save(vaccine));
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
                .orElseThrow(() -> new RuntimeException("Vacuna no encontrada"));
        mapper.updateEntity(dto, vaccine);
        return mapper.toDto(repository.save(vaccine));
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        if (!repository.existsById(id)) {
            throw new RuntimeException("Vacuna no encontrada para eliminar");
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
                .orElseThrow(() -> new RuntimeException("Vacuna no encontrada"));
    }

    @Override
    public List<VaccinationHistoryReadDTO> getAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
