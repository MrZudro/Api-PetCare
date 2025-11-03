package edu.sena.petcare.services.vaccine;

import edu.sena.petcare.dto.vaccine.VaccineCreateDTO;
import edu.sena.petcare.dto.vaccine.VaccineReadDTO;
import edu.sena.petcare.dto.vaccine.VaccineUpdateDTO;
import edu.sena.petcare.mapper.VaccineMapper;
import edu.sena.petcare.models.Vaccine;
import edu.sena.petcare.repositories.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VaccineServiceImpl implements VaccineService {

    @Autowired
    private VaccineRepository vaccineRepository;

    @Override
    public VaccineReadDTO create(VaccineCreateDTO dto) {
        Vaccine vaccine = VaccineMapper.toEntity(dto);
        vaccineRepository.save(vaccine);
        return VaccineMapper.toReadDTO(vaccine);
    }

    @Override
    public List<VaccineReadDTO> findAll() {
        return vaccineRepository.findAll().stream()
                .map(VaccineMapper::toReadDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VaccineReadDTO findById(Long id) {
        Vaccine vaccine = vaccineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vaccine not found with id: " + id));
        return VaccineMapper.toReadDTO(vaccine);
    }

    @Override
    public VaccineReadDTO update(Long id, VaccineUpdateDTO dto) {
        Vaccine vaccine = vaccineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vaccine not found with id: " + id));

        vaccine.setName(dto.getName());
        vaccine.setDescription(dto.getDescription());
        vaccine.setApplicationDate(dto.getApplicationDate());
        vaccine.setActive(dto.isActive());

        vaccineRepository.save(vaccine);
        return VaccineMapper.toReadDTO(vaccine);
    }

    @Override
    public void deactivate(Long id) {
        Vaccine vaccine = vaccineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vaccine not found with id: " + id));
        vaccine.setActive(false);
        vaccineRepository.save(vaccine);
    }
}
