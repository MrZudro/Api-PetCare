package edu.sena.petcare.services.veterinaryclinic;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.sena.petcare.dto.veterinaryclinic.VeterinaryClinicReadDTO;
import edu.sena.petcare.mapper.VeterinaryClinicMapper;
import edu.sena.petcare.repositories.VeterinaryClinicRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VeterinaryClinicServiceImpl implements VeterinaryClinicService {

    private final VeterinaryClinicRepository veterinaryClinicRepository;
    private final VeterinaryClinicMapper veterinaryClinicMapper;

    @Override
    public List<VeterinaryClinicReadDTO> getAll() {
        return veterinaryClinicRepository.findAll()
                .stream()
                .map(veterinaryClinicMapper::toDto)
                .toList();
    }

    @Override
    public VeterinaryClinicReadDTO getById(Long id) {
        return veterinaryClinicRepository.findById(id)
                .map(veterinaryClinicMapper::toDto)
                .orElseThrow(() -> new RuntimeException("VeterinaryClinic not found with id: " + id));
    }
}
