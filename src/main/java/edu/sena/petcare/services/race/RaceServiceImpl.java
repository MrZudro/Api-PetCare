package edu.sena.petcare.services.race;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import edu.sena.petcare.dto.race.RaceReadDTO;
import edu.sena.petcare.mapper.RaceMapper;
import edu.sena.petcare.repositories.RaceRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RaceServiceImpl implements RaceService {

    private final RaceRepository raceRepository;
    private final RaceMapper raceMapper;

    @Override
    public List<RaceReadDTO> getAll() {
        return raceRepository.findAll()
                .stream()
                .map(raceMapper::toReadDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RaceReadDTO getById(Long id) {
        return raceRepository.findById(id)
                .map(raceMapper::toReadDTO)
                .orElseThrow(() -> new RuntimeException("Race not found with id: " + id));
    }
}
