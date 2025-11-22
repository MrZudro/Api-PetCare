package edu.sena.petcare.services.pet;

import edu.sena.petcare.dto.Pet.PetCreateDTO;
import edu.sena.petcare.dto.Pet.PetReadDTO;
import edu.sena.petcare.dto.Pet.PetUpdateDTO;
import edu.sena.petcare.mapper.PetMapper;
import edu.sena.petcare.models.Pet;
import edu.sena.petcare.models.Race;
import edu.sena.petcare.models.User;
import edu.sena.petcare.repositories.PetRepository;
import edu.sena.petcare.repositories.RaceRepository;
import edu.sena.petcare.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;
    private final PetMapper petMapper;
    private final RaceRepository raceRepository;
    private final UserRepository userRepository;

    private static final String NOT_FOUND_MSG = "Mascota no encontrada";

    @Override
    public PetReadDTO create(PetCreateDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("dto cannot be null");
        }
        if (dto.getIdRace() == null) {
            throw new IllegalArgumentException("idRace cannot be null");
        }
        if (dto.getIdUser() == null) {
            throw new IllegalArgumentException("idUser cannot be null");
        }

        Race race = raceRepository.findById(Objects.requireNonNull(dto.getIdRace(), "Race ID cannot be null"))
                .orElseThrow(() -> new RuntimeException("Raza no encontrada"));

        User user = userRepository.findById(Objects.requireNonNull(dto.getIdUser(), "User ID cannot be null"))
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Pet pet = petMapper.toEntity(dto);
        pet.setRaza(race);
        pet.setUser(user);

        @SuppressWarnings("null")
        Pet savedPet = petRepository.save(pet);
        return petMapper.toDto(savedPet);
    }

    @Override
    public PetReadDTO update(Long id, PetUpdateDTO dto) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        if (dto == null) {
            throw new IllegalArgumentException("dto cannot be null");
        }
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(NOT_FOUND_MSG));

        // Actualizar campos simples
        petMapper.updateEntity(dto, pet);

        @SuppressWarnings("null")
        Pet updatedPet = petRepository.save(pet);
        return petMapper.toDto(updatedPet);
    }

    @Override
    public void deactivate(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(NOT_FOUND_MSG));
        String currentMicrochip = pet.getMicrochip() != null ? pet.getMicrochip() : "";
        pet.setMicrochip(currentMicrochip + "_INACTIVA");
        petRepository.save(pet);
    }

    @Override
    public PetReadDTO getById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        return petRepository.findById(id)
                .map(petMapper::toDto)
                .orElseThrow(() -> new RuntimeException(NOT_FOUND_MSG));
    }

    @Override
    public List<PetReadDTO> getAllByUser(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("userId cannot be null");
        }
        return petRepository.findAll().stream()
                .filter(p -> p.getUser() != null && userId.equals(p.getUser().getId()))
                .map(petMapper::toDto)
                .toList();
    }
}
