package edu.sena.petcare.services.species;


import edu.sena.petcare.dto.species.SpeciesNewUpdateDTO;
import edu.sena.petcare.dto.species.SpeciesReadDTO;
import edu.sena.petcare.exceptions.DuplicateResourceException;
import edu.sena.petcare.exceptions.ResourceNotFoundException;
import edu.sena.petcare.mapper.SpeciesMapper;
import edu.sena.petcare.models.Species;
import edu.sena.petcare.models.enums.SpeciesStatus;
import edu.sena.petcare.repositories.SpeciesRepository;
import edu.sena.petcare.services.species.SpeciesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpeciesServiceImpl implements SpeciesService {

    private final SpeciesRepository speciesRepository;
    private final SpeciesMapper speciesMapper;

    @Override
    @Transactional(readOnly = true)
    public List<SpeciesReadDTO> findAllActive() {
        return speciesRepository.findAllByStatus(SpeciesStatus.ACTIVE).stream()
                .map(speciesMapper::toReadDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public SpeciesReadDTO findByIdActive(Long id) {
        Species species = speciesRepository.findByIdAndStatus(id, SpeciesStatus.ACTIVE)
                .orElseThrow(() -> new ResourceNotFoundException("Especie no encontrada o inactiva con id: " + id));
        return speciesMapper.toReadDTO(species);
    }

    @Override
    @Transactional
    public SpeciesReadDTO create(SpeciesNewUpdateDTO dto) {
        // 1. Validar nombre único
        speciesRepository.findByNameIgnoreCase(dto.getName()).ifPresent(e -> {
            throw new DuplicateResourceException("Ya existe una especie con el nombre: " + dto.getName());
        });

        // 2. Mapear DTO a Entidad
        Species species = speciesMapper.toEntity(dto);

        // 3. Establecer estado inicial como ACTIVO
        species.setStatus(SpeciesStatus.ACTIVE);

        // 4. Guardar
        Species savedSpecies = speciesRepository.save(species);
        return speciesMapper.toReadDTO(savedSpecies);
    }

    @Override
    @Transactional
    public SpeciesReadDTO update(Long id, SpeciesNewUpdateDTO dto) {
        // 1. Buscar especie (debe estar activa para editarla)
        Species species = speciesRepository.findByIdAndStatus(id, SpeciesStatus.ACTIVE)
                .orElseThrow(() -> new ResourceNotFoundException("Especie no encontrada o inactiva con id: " + id));

        // 2. Validar nombre único (si el nombre cambió)
        if (!species.getName().equalsIgnoreCase(dto.getName())) {
            speciesRepository.findByNameIgnoreCase(dto.getName()).ifPresent(e -> {
                throw new DuplicateResourceException("Ya existe otra especie con el nombre: " + dto.getName());
            });
        }

        // 3. Actualizar la entidad con el DTO
        speciesMapper.updateEntityFromDto(dto, species);

        // 4. Guardar
        Species updatedSpecies = speciesRepository.save(species);
        return speciesMapper.toReadDTO(updatedSpecies);
    }

    @Override
    @Transactional
    public void deactivate(Long id) {
        // 1. Buscar especie (debe estar activa para poder desactivarla)
        Species species = speciesRepository.findByIdAndStatus(id, SpeciesStatus.ACTIVE)
                .orElseThrow(() -> new ResourceNotFoundException("Especie no encontrada o ya inactiva con id: " + id));

        // 2. Eliminación Lógica
        species.setStatus(SpeciesStatus.INACTIVE);

        // 3. Guardar el cambio de estado
        speciesRepository.save(species);
    }
}