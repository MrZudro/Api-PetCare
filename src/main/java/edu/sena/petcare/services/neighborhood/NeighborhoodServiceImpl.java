package edu.sena.petcare.services.neighborhood;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import edu.sena.petcare.dto.neighborhood.NeighborhoodReadDTO;
import edu.sena.petcare.mapper.NeighborhoodMapper;
import edu.sena.petcare.repositories.NeighborhoodRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NeighborhoodServiceImpl implements NeighborhoodService {

    private final NeighborhoodRepository neighborhoodRepository;
    private final NeighborhoodMapper neighborhoodMapper;

    @Override
    public List<NeighborhoodReadDTO> getAll() {
        return neighborhoodRepository.findAll()
                .stream()
                .map(neighborhoodMapper::toReadDTO)
                .collect(Collectors.toList());
    }

    @Override
    public NeighborhoodReadDTO getById(Long id) {
        return neighborhoodRepository.findById(id)
                .map(neighborhoodMapper::toReadDTO)
                .orElseThrow(() -> new RuntimeException("Neighborhood not found with id: " + id));
    }
}
