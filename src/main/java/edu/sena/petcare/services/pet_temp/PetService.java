package edu.sena.petcare.services.pet_temp;

import edu.sena.petcare.dto.Pet.PetCreateDTO;
import edu.sena.petcare.dto.Pet.PetReadDTO;
import edu.sena.petcare.dto.Pet.PetUpdateDTO;
import java.util.List;

public interface PetService {
    PetReadDTO create(PetCreateDTO dto);

    PetReadDTO update(Long id, PetUpdateDTO dto);

    void deactivate(Long id);

    PetReadDTO getById(Long id);

    List<PetReadDTO> getAllByUser(Long userId);

    edu.sena.petcare.dto.Pet.PetDetailDTO getPetDetailById(Long id);
}
