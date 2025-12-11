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
                .map(neighborhoodMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public NeighborhoodReadDTO getById(Long id) {
        return neighborhoodRepository.findById(id)
                .map(neighborhoodMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Neighborhood not found with id: " + id));
    }

    @Override
    public List<NeighborhoodReadDTO> getByLocalityId(Long localityId) {
        return neighborhoodRepository.findByLocalityId(localityId)
                .stream()
                .map(neighborhoodMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<NeighborhoodReadDTO> getByLocalityIdSorted(Long localityId) {
        return neighborhoodRepository.findByLocalityId(localityId)
                .stream()
                .sorted((n1, n2) -> n1.getName().compareToIgnoreCase(n2.getName()))
                .map(neighborhoodMapper::toDto)
                .collect(Collectors.toList());
    }
}
