package edu.sena.petcare.services.pet;


import edu.sena.petcare.dto.pet.PetNewUpdateDTO;
import edu.sena.petcare.dto.pet.PetReadDTO;
import java.util.List;

public interface PetService {

    List<PetReadDTO> findAllActive();
    PetReadDTO findByIdActive(Long id);
    PetReadDTO create(PetNewUpdateDTO dto);
    PetReadDTO update(Long id, PetNewUpdateDTO dto);

    /**
     * Desactiva una mascota (Eliminación Lógica).
     * Cambia su estado a INACTIVE.
     */
    void deactivate(Long id);
}