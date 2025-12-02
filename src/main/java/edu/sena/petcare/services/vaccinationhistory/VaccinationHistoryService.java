package edu.sena.petcare.services.vaccinationhistory;

import java.util.List;
import java.util.Optional;

import edu.sena.petcare.dto.vaccinationhistory.VaccinationHistoryNewUpdateDTO;
import edu.sena.petcare.dto.vaccinationhistory.VaccinationHistoryReadDTO;

public interface VaccinationHistoryService {
    VaccinationHistoryReadDTO create(VaccinationHistoryNewUpdateDTO dto);

    VaccinationHistoryReadDTO update(Long id, VaccinationHistoryNewUpdateDTO dto);

    void delete(Long id);

    Optional<VaccinationHistoryReadDTO> getById(Long id);

    List<VaccinationHistoryReadDTO> getAll();

    List<VaccinationHistoryReadDTO> getByPetId(Long petId);
}
