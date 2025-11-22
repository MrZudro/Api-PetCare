package edu.sena.petcare.services.vaccinationhistory;

import edu.sena.petcare.dto.VaccinationHistory.VaccinationHistoryCreateDTO;
import edu.sena.petcare.dto.VaccinationHistory.VaccinationHistoryReadDTO;
import edu.sena.petcare.dto.VaccinationHistory.VaccinationHistoryUpdateDTO;
import java.util.List;

public interface VaccinationHistoryService {
    VaccinationHistoryReadDTO create(VaccinationHistoryCreateDTO dto);

    VaccinationHistoryReadDTO update(Long id, VaccinationHistoryUpdateDTO dto);

    void delete(Long id);

    VaccinationHistoryReadDTO getById(Long id);

    List<VaccinationHistoryReadDTO> getAll();
}
