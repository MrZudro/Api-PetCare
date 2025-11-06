package edu.sena.petcare.services.Pet;

import edu.sena.petcare.dto.Pet.PetReadDTO;
import edu.sena.petcare.dto.Pet.PetCreateDTO;
import edu.sena.petcare.dto.Pet.PetUpdateDTO;
import edu.sena.petcare.mapper.PetMapper;
import edu.sena.petcare.models.*;
import edu.sena.petcare.repositories.*;
import edu.sena.petcare.services.Pet.PetService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;
    private final RaceRepository raceRepository;
    private final UserRepository userRepository;

    public PetServiceImpl(PetRepository petRepository, RaceRepository raceRepository, UserRepository userRepository) {
        this.petRepository = petRepository;
        this.raceRepository = raceRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PetReadDTO create(PetCreateDTO dto) {
        Race race = raceRepository.findById(dto.getIdRace())
                .orElseThrow(() -> new RuntimeException("Raza no encontrada"));

        User user = userRepository.findById(dto.getIdUser())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Pet pet = PetMapper.toEntity(dto, race, user);
        petRepository.save(pet);
        return PetMapper.toReadDTO(pet);
    }

    @Override
    public PetReadDTO update(Long id, PetUpdateDTO dto) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));
        PetMapper.updateEntity(pet, dto);
        petRepository.save(pet);
        return PetMapper.toReadDTO(pet);
    }

    @Override
    public void deactivate(Long id) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));
        pet.setMicrochip(pet.getMicrochip() + "_INACTIVA");
        petRepository.save(pet);
    }


    @Override
    public PetReadDTO getById(Long id) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));
        return PetMapper.toReadDTO(pet);
    }

    @Override
    public List<PetReadDTO> getAllByUser(Long userId) {
        return petRepository.findAll().stream()
                .filter(p -> p.getUser() != null && p.getUser().getId().equals(userId))
                .map(PetMapper::toReadDTO)
                .collect(Collectors.toList());
    }
}
