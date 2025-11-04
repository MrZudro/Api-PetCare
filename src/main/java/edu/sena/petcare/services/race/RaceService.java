package edu.sena.petcare.services.race;

import edu.sena.petcare.dto.race.RaceNewUpdateDTO;
import edu.sena.petcare.dto.race.RaceReadDTO;
import java.util.List;

public interface RaceService {

    List<RaceReadDTO> findAllActive();
    RaceReadDTO findByIdActive(Long id);
    RaceReadDTO create(RaceNewUpdateDTO dto);
    RaceReadDTO update(Long id, RaceNewUpdateDTO dto);
    void deactivate(Long id);
}
