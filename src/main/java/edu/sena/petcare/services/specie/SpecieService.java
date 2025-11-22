package edu.sena.petcare.services.specie;

import java.util.List;

import edu.sena.petcare.dto.specie.SpecieReadDTO;

public interface SpecieService {
    List<SpecieReadDTO> getAll();

    SpecieReadDTO getById(Long id);
}
