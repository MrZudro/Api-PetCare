package edu.sena.petcare.services.vaccinationhistory;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.sena.petcare.dto.vaccinationhistory.VaccinationHistoryNewUpdateDTO;
import edu.sena.petcare.dto.vaccinationhistory.VaccinationHistoryReadDTO;
import edu.sena.petcare.mapper.VaccinationHistoryMapper;
import edu.sena.petcare.models.VaccinationHistory;
import edu.sena.petcare.repositories.VaccinationHistoryRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class VaccinationHistoryServiceImpl implements VaccinationHistoryService {

    private final VaccinationHistoryRepository repository;
    private final VaccinationHistoryMapper mapper;

    @Override
    public VaccinationHistoryReadDTO create(VaccinationHistoryNewUpdateDTO dto) {
        VaccinationHistory entity = mapper.toEntity(dto);
        VaccinationHistory saved = repository.save(entity);
        return mapper.toReadDTO(saved);
    }

    @Override
    public VaccinationHistoryReadDTO update(Long id, VaccinationHistoryNewUpdateDTO dto) {
        VaccinationHistory entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vaccination History not found with id: " + id));

        mapper.updateEntity(dto, entity);
        VaccinationHistory updated = repository.save(entity);
        return mapper.toReadDTO(updated);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Vaccination History not found with id: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public Optional<VaccinationHistoryReadDTO> getById(Long id) {
        return repository.findById(id)
                .map(mapper::toReadDTO);
    }

    @Override
    public List<VaccinationHistoryReadDTO> getAll() {
        List<VaccinationHistory> list = repository.findAll();
        return mapper.toReadDtoList(list);
    }

    @Override
    public List<VaccinationHistoryReadDTO> getByPetId(Long petId) {
        List<VaccinationHistory> list = repository.findByPetId(petId);
        return mapper.toReadDtoList(list);
    }
}
