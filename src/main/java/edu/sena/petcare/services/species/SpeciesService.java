package edu.sena.petcare.services.species;


import edu.sena.petcare.dto.species.SpeciesNewUpdateDTO;
import edu.sena.petcare.dto.species.SpeciesReadDTO;
import java.util.List;

public interface SpeciesService {

    List<SpeciesReadDTO> findAllActive();
    SpeciesReadDTO findByIdActive(Long id);
    SpeciesReadDTO create(SpeciesNewUpdateDTO dto);
    SpeciesReadDTO update(Long id, SpeciesNewUpdateDTO dto);
    void deactivate(Long id);
}