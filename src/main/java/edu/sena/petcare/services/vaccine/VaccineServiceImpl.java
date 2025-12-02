package edu.sena.petcare.services.vaccine;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.sena.petcare.dto.vaccine.VaccineNewUpdateDTO;
import edu.sena.petcare.dto.vaccine.VaccineReadDTO;
import edu.sena.petcare.mapper.VaccineMapper;
import edu.sena.petcare.models.Vaccine;
import edu.sena.petcare.repositories.VaccineRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class VaccineServiceImpl implements VaccineService {

    private final VaccineRepository vaccineRepository;
    private final VaccineMapper vaccineMapper;

    @Override
    public VaccineReadDTO createVaccine(VaccineNewUpdateDTO vaccineDTO) {
        Vaccine vaccine = vaccineMapper.toEntity(vaccineDTO);
        Vaccine saved = vaccineRepository.save(vaccine);
        return vaccineMapper.toReadDTO(saved);
    }

    @Override
    public List<VaccineReadDTO> getAllVaccines() {
        List<Vaccine> vaccines = vaccineRepository.findAll();
        return vaccineMapper.toReadDtoList(vaccines);
    }

    @Override
    public Optional<VaccineReadDTO> getVaccineById(Long id) {
        return vaccineRepository.findById(id)
                .map(vaccineMapper::toReadDTO);
    }

    @Override
    public VaccineReadDTO updateVaccine(Long id, VaccineNewUpdateDTO vaccineDTO) {
        Vaccine vaccine = vaccineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vaccine not found with id: " + id));

        vaccineMapper.updateEntity(vaccineDTO, vaccine);
        Vaccine updated = vaccineRepository.save(vaccine);
        return vaccineMapper.toReadDTO(updated);
    }

    @Override
    public void deleteVaccine(Long id) {
        if (!vaccineRepository.existsById(id)) {
            throw new RuntimeException("Vaccine not found with id: " + id);
        }
        vaccineRepository.deleteById(id);
    }
}
