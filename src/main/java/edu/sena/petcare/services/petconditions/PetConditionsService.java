package edu.sena.petcare.services.petconditions;

import java.util.List;

import edu.sena.petcare.dto.petconditions.PetConditionsReadDTO;

public interface PetConditionsService {
    List<PetConditionsReadDTO> getAll();

    PetConditionsReadDTO getById(Long id);
}
