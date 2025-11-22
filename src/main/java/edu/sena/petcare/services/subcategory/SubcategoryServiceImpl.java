package edu.sena.petcare.services.subcategory;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import edu.sena.petcare.dto.subcategory.SubcategoryReadDTO;
import edu.sena.petcare.mapper.SubcategoryMapper;
import edu.sena.petcare.repositories.SubcategoryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubcategoryServiceImpl implements SubcategoryService {

    private final SubcategoryRepository subcategoryRepository;
    private final SubcategoryMapper subcategoryMapper;

    @Override
    public List<SubcategoryReadDTO> getAll() {
        return subcategoryRepository.findAll()
                .stream()
                .map(subcategoryMapper::toReadDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SubcategoryReadDTO getById(Long id) {
        return subcategoryRepository.findById(id)
                .map(subcategoryMapper::toReadDTO)
                .orElseThrow(() -> new RuntimeException("Subcategory not found with id: " + id));
    }
}
