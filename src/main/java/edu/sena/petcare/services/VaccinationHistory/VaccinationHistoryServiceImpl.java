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

    public VaccinationHistoryServiceImpl(VaccinationHistoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public VaccinationHistoryReadDTO create(VaccinationHistoryCreateDTO dto) {
        VaccinationHistory vaccine = VaccinationHistoryMapper.toEntity(dto);
        return VaccinationHistoryMapper.toReadDTO(repository.save(vaccine));
    }

    @Override
    public VaccinationHistoryReadDTO update(Long id, VaccinationHistoryUpdateDTO dto) {
        VaccinationHistory vaccine = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vacuna no encontrada"));
        VaccinationHistoryMapper.updateEntity(vaccine, dto);
        return VaccinationHistoryMapper.toReadDTO(repository.save(vaccine));
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Vacuna no encontrada para eliminar");
        }
        repository.deleteById(id);
    }

    @Override
    public VaccinationHistoryReadDTO getById(Long id) {
        return repository.findById(id)
                .map(VaccinationHistoryMapper::toReadDTO)
                .orElseThrow(() -> new RuntimeException("Vacuna no encontrada"));
    }

    @Override
    public List<VaccinationHistoryReadDTO> getAll() {
        return repository.findAll().stream()
                .map(VaccinationHistoryMapper::toReadDTO)
                .collect(Collectors.toList());
    }
}
