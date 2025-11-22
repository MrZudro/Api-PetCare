package edu.sena.petcare.services.locality;

import java.util.List;

import edu.sena.petcare.dto.locality.LocalityReadDTO;

public interface LocalityService {
    List<LocalityReadDTO> getAll();

    LocalityReadDTO getById(Long id);
}
