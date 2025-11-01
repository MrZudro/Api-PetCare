package edu.sena.petcare.services.pet;


import edu.sena.petcare.dto.pet.PetNewUpdateDTO;
import edu.sena.petcare.dto.pet.PetReadDTO;
import edu.sena.petcare.exceptions.DuplicateResourceException;
import edu.sena.petcare.exceptions.ResourceNotFoundException;
import edu.sena.petcare.mapper.PetMapper;
import edu.sena.petcare.models.Pet;
import edu.sena.petcare.models.Race;
import edu.sena.petcare.models.User;
import edu.sena.petcare.models.enums.PetStatus;
import edu.sena.petcare.repositories.PetRepository;
import edu.sena.petcare.repositories.RaceRepository;
import edu.sena.petcare.repositories.UserRepository;
import edu.sena.petcare.services.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;
    private final RaceRepository raceRepository; // Para validar la Raza
    private final UserRepository userRepository; // Para validar el Dueño
    private final PetMapper petMapper;

    @Override
    @Transactional(readOnly = true)
    public List<PetReadDTO> findAllActive() {
        return petRepository.findAllByStatus(PetStatus.ACTIVE).stream()
                .map(petMapper::toReadDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public PetReadDTO findByIdActive(Long id) {
        Pet pet = petRepository.findByIdAndStatus(id, PetStatus.ACTIVE)
                .orElseThrow(() -> new ResourceNotFoundException("Mascota no encontrada o inactiva con id: " + id));
        return petMapper.toReadDTO(pet);
    }

    @Override
    @Transactional
    public PetReadDTO create(PetNewUpdateDTO dto) {
        // 1. Validar microchip único (si se proporciona)
        if (dto.getMicrochip() != null && !dto.getMicrochip().isEmpty()) {
            petRepository.findByMicrochip(dto.getMicrochip()).ifPresent(p -> {
                throw new DuplicateResourceException("Ya existe una mascota con el microchip: " + dto.getMicrochip());
            });
        }

        // 2. Validar existencia de la Raza y el Usuario
        Race race = raceRepository.findById(dto.getRaceId())
                .orElseThrow(() -> new ResourceNotFoundException("Raza no encontrada con id: " + dto.getRaceId()));

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario (dueño) no encontrado con id: " + dto.getUserId()));

        // 3. Mapear DTO a Entidad
        Pet pet = petMapper.toEntity(dto);

        // Asignar las relaciones y el estado
        pet.setRace(race);
        pet.setUser(user);
        pet.setStatus(PetStatus.ACTIVE);

        // 4. Guardar
        Pet savedPet = petRepository.save(pet);
        return petMapper.toReadDTO(savedPet);
    }

    @Override
    @Transactional
    public PetReadDTO update(Long id, PetNewUpdateDTO dto) {
        // 1. Buscar mascota (debe estar activa para editarla)
        Pet pet = petRepository.findByIdAndStatus(id, PetStatus.ACTIVE)
                .orElseThrow(() -> new ResourceNotFoundException("Mascota no encontrada o inactiva con id: " + id));

        // 2. Validar microchip único (si se proporciona y si cambió)
        if (dto.getMicrochip() != null && !dto.getMicrochip().isEmpty() && !dto.getMicrochip().equals(pet.getMicrochip())) {
            petRepository.findByMicrochip(dto.getMicrochip()).ifPresent(p -> {
                throw new DuplicateResourceException("Ya existe otra mascota con el microchip: " + dto.getMicrochip());
            });
        }

        // Las relaciones Race y User NO se actualizan con el DTO de actualización
        // Si fuera necesario cambiarlas, se requeriría un DTO y lógica aparte.

        // 3. Actualizar la entidad con el DTO (solo campos directos)
        petMapper.updateEntityFromDto(dto, pet);

        // 4. Guardar
        Pet updatedPet = petRepository.save(pet);
        return petMapper.toReadDTO(updatedPet);
    }

    @Override
    @Transactional
    public void deactivate(Long id) {
        // 1. Buscar mascota (debe estar activa para poder desactivarla)
        Pet pet = petRepository.findByIdAndStatus(id, PetStatus.ACTIVE)
                .orElseThrow(() -> new ResourceNotFoundException("Mascota no encontrada o ya inactiva con id: " + id));

        // 2. Eliminación Lógica
        pet.setStatus(PetStatus.INACTIVE);

        // 3. Guardar el cambio de estado
        petRepository.save(pet);
    }
}