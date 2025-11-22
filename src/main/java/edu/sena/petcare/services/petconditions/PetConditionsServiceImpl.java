package edu.sena.petcare.services.petconditions;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.sena.petcare.dto.petconditions.PetConditionsReadDTO;
import edu.sena.petcare.mapper.PetConditionsMapper;
import edu.sena.petcare.repositories.PetConditionsRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PetConditionsServiceImpl implements PetConditionsService {

    private final PetConditionsRepository petConditionsRepository;
    private final PetConditionsMapper petConditionsMapper;

    @Override
    public List<PetConditionsReadDTO> getAll() {
        return petConditionsRepository.findAll()
                .stream()
                .map(petConditionsMapper::toDto)
                .toList();
    }

    @Override
    public PetConditionsReadDTO getById(Long id) {
        return petConditionsRepository.findById(id)
                .map(petConditionsMapper::toDto)
                .orElseThrow(() -> new RuntimeException("PetConditions not found with id: " + id));
    }
}
