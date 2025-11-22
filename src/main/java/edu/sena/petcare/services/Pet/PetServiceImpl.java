package edu.sena.petcare.services.Pet;

import edu.sena.petcare.dto.Pet.PetReadDTO;
import edu.sena.petcare.dto.Pet.PetCreateDTO;
import edu.sena.petcare.dto.Pet.PetUpdateDTO;
import edu.sena.petcare.mapper.PetMapper;
import edu.sena.petcare.models.*;
import edu.sena.petcare.repositories.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;
    private final RaceRepository raceRepository;
    private final UserRepository userRepository;
    private final PetMapper petMapper;

    public PetServiceImpl(PetRepository petRepository, RaceRepository raceRepository, UserRepository userRepository,
            PetMapper petMapper) {
        this.petRepository = petRepository;
        this.raceRepository = raceRepository;
        this.userRepository = userRepository;
        this.petMapper = petMapper;
    }

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

        Race race = raceRepository.findById(dto.getIdRace())
                .orElseThrow(() -> new RuntimeException("Raza no encontrada"));

        User user = userRepository.findById(dto.getIdUser())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Pet pet = petMapper.toEntity(dto);
        pet.setRaza(race);
        pet.setUser(user);

        petRepository.save(pet);
        return petMapper.toDto(pet);
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
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));
        petMapper.updateEntity(dto, pet);
        petRepository.save(pet);
        return petMapper.toDto(pet);
    }

    @Override
    public void deactivate(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));
        String currentMicrochip = pet.getMicrochip() != null ? pet.getMicrochip() : "";
        pet.setMicrochip(currentMicrochip + "_INACTIVA");
        petRepository.save(pet);
    }

    @Override
    public PetReadDTO getById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));
        return petMapper.toDto(pet);
    }

    @Override
    public List<PetReadDTO> getAllByUser(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("userId cannot be null");
        }
        return petRepository.findAll().stream()
                .filter(p -> p.getUser() != null && userId.equals(p.getUser().getId()))
                .map(petMapper::toDto)
                .collect(Collectors.toList());
    }
}
