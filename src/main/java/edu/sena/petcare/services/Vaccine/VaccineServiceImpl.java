package edu.sena.petcare.services.Vaccine;

import edu.sena.petcare.dto.Vaccine.VaccineCreateDTO;
import edu.sena.petcare.dto.Vaccine.VaccineReadDTO;
import edu.sena.petcare.dto.Vaccine.VaccineUpdateDTO;
import edu.sena.petcare.mapper.VaccineMapper;
import edu.sena.petcare.models.Vaccine;
import edu.sena.petcare.repositories.VaccineRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VaccineServiceImpl implements VaccineService {

    private final VaccineRepository repository;

    public VaccineServiceImpl(VaccineRepository repository) {
        this.repository = repository;
    }

    @Override
    public VaccineReadDTO create(VaccineCreateDTO dto) {
        Vaccine vaccine = VaccineMapper.toEntity(dto);
        return VaccineMapper.toReadDTO(repository.save(vaccine));
    }

    @Override
    public VaccineReadDTO update(Long id, VaccineUpdateDTO dto) {
        Vaccine vaccine = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vacuna no encontrada"));
        VaccineMapper.updateEntity(vaccine, dto);
        return VaccineMapper.toReadDTO(repository.save(vaccine));
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Vacuna no encontrada para eliminar");
        }
        repository.deleteById(id);
    }

    @Override
    public VaccineReadDTO getById(Long id) {
        return repository.findById(id)
                .map(VaccineMapper::toReadDTO)
                .orElseThrow(() -> new RuntimeException("Vacuna no encontrada"));
    }

    @Override
    public List<VaccineReadDTO> getAll() {
        return repository.findAll().stream()
                .map(VaccineMapper::toReadDTO)
                .collect(Collectors.toList());
    }
}
