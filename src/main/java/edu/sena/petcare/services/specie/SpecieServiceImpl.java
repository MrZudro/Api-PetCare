package edu.sena.petcare.services.specie;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.sena.petcare.dto.specie.SpecieReadDTO;
import edu.sena.petcare.mapper.SpecieMapper;
import edu.sena.petcare.repositories.SpecieRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SpecieServiceImpl implements SpecieService {

    private final SpecieRepository specieRepository;
    private final SpecieMapper specieMapper;

    @Override
    public List<SpecieReadDTO> getAll() {
        return specieRepository.findAll()
                .stream()
                .map(specieMapper::toDto)
                .toList();
    }

    @Override
    public SpecieReadDTO getById(Long id) {
        return specieRepository.findById(id)
                .map(specieMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Specie not found with id: " + id));
    }
}
