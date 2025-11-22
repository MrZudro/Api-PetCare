package edu.sena.petcare.services.race;

import java.util.List;

import edu.sena.petcare.dto.race.RaceReadDTO;

public interface RaceService {
    List<RaceReadDTO> getAll();

    RaceReadDTO getById(Long id);
}
