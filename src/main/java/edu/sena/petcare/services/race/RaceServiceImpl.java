package edu.sena.petcare.services.race;


import edu.sena.petcare.dto.race.RaceNewUpdateDTO;
import edu.sena.petcare.dto.race.RaceReadDTO;
import edu.sena.petcare.exceptions.DuplicateResourceException;
import edu.sena.petcare.exceptions.ResourceNotFoundException;
import edu.sena.petcare.mapper.RaceMapper;
import edu.sena.petcare.models.Race;
import edu.sena.petcare.models.Specie;
import edu.sena.petcare.models.enums.RaceStatus;
import edu.sena.petcare.repositories.RaceRepository;
import edu.sena.petcare.repositories.SpeciesRepository; // Repositorio de Species (asumido)
import edu.sena.petcare.services.RaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RaceServiceImpl implements RaceService {

    private final RaceRepository raceRepository;
    private final SpeciesRepository speciesRepository; // Asumido
    private final RaceMapper raceMapper;

    @Override
    @Transactional(readOnly = true)
    public List<RaceReadDTO> findAllActive() {
        return raceRepository.findAllByStatus(RaceStatus.ACTIVE).stream()
                .map(raceMapper::toReadDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public RaceReadDTO findByIdActive(Long id) {
        Race race = raceRepository.findByIdAndStatus(id, RaceStatus.ACTIVE)
                .orElseThrow(() -> new ResourceNotFoundException("Raza no encontrada o inactiva con id: " + id));
        return raceMapper.toReadDTO(race);
    }

    @Override
    @Transactional
    public RaceReadDTO create(RaceNewUpdateDTO dto) {
        // 1. Validar nombre único
        raceRepository.findByNameIgnoreCase(dto.getName()).ifPresent(e -> {
            throw new DuplicateResourceException("Ya existe una raza con el nombre: " + dto.getName());
        });

        // 2. Validar existencia de la Especie
        Species species = speciesRepository.findById(dto.getSpeciesId())
                .orElseThrow(() -> new ResourceNotFoundException("Especie no encontrada con id: " + dto.getSpeciesId()));

        // 3. Mapear DTO a Entidad y asignar relaciones
        Race race = raceMapper.toEntity(dto);
        race.setSpecies(species);
        race.setStatus(RaceStatus.ACTIVE);

        // 4. Guardar
        Race savedRace = raceRepository.save(race);
        return raceMapper.toReadDTO(savedRace);
    }

    @Override
    @Transactional
    public RaceReadDTO update(Long id, RaceNewUpdateDTO dto) {
        // 1. Buscar raza (debe estar activa para editarla)
        Race race = raceRepository.findByIdAndStatus(id, RaceStatus.ACTIVE)
                .orElseThrow(() -> new ResourceNotFoundException("Raza no encontrada o inactiva con id: " + id));

        // 2. Validar nombre único (si el nombre cambió)
        if (!race.getName().equalsIgnoreCase(dto.getName())) {
            raceRepository.findByNameIgnoreCase(dto.getName()).ifPresent(e -> {
                throw new DuplicateResourceException("Ya existe otra raza con el nombre: " + dto.getName());
            });
        }

        // 3. Actualizar la entidad con el DTO (solo campos directos como el nombre)
        raceMapper.updateEntityFromDto(dto, race);

        // Nota: Si el speciesId del DTO cambió, se debe actualizar la relación aquí:
        if (!dto.getSpeciesId().equals(race.getSpecies().getId())) {
            Species newSpecies = speciesRepository.findById(dto.getSpeciesId())
                    .orElseThrow(() -> new ResourceNotFoundException("Nueva Especie no encontrada con id: " + dto.getSpeciesId()));
            race.setSpecies(newSpecies);
        }

        // 4. Guardar
        Race updatedRace = raceRepository.save(race);
        return raceMapper.toReadDTO(updatedRace);
    }

    @Override
    @Transactional
    public void deactivate(Long id) {
        // 1. Buscar raza (debe estar activa para poder desactivarla)
        Race race = raceRepository.findByIdAndStatus(id, RaceStatus.ACTIVE)
                .orElseThrow(() -> new ResourceNotFoundException("Raza no encontrada o ya inactiva con id: " + id));

        // 2. Eliminación Lógica
        race.setStatus(RaceStatus.INACTIVE);

        // 3. Guardar el cambio de estado
        raceRepository.save(race);
    }
}
