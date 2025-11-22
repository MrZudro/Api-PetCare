package edu.sena.petcare.services.locality;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import edu.sena.petcare.dto.locality.LocalityReadDTO;
import edu.sena.petcare.mapper.LocalityMapper;
import edu.sena.petcare.repositories.LocalityRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocalityServiceImpl implements LocalityService {

    private final LocalityRepository localityRepository;
    private final LocalityMapper localityMapper;

    @Override
    public List<LocalityReadDTO> getAll() {
        return localityRepository.findAll()
                .stream()
                .map(localityMapper::toReadDTO)
                .collect(Collectors.toList());
    }

    @Override
    public LocalityReadDTO getById(Long id) {
        return localityRepository.findById(id)
                .map(localityMapper::toReadDTO)
                .orElseThrow(() -> new RuntimeException("Locality not found with id: " + id));
    }
}
