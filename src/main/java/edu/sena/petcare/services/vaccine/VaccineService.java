package edu.sena.petcare.services.vaccine;

import java.util.List;
import java.util.Optional;

import edu.sena.petcare.dto.vaccine.VaccineNewUpdateDTO;
import edu.sena.petcare.dto.vaccine.VaccineReadDTO;

public interface VaccineService {
    VaccineReadDTO createVaccine(VaccineNewUpdateDTO vaccineDTO);

    List<VaccineReadDTO> getAllVaccines();

    Optional<VaccineReadDTO> getVaccineById(Long id);

    VaccineReadDTO updateVaccine(Long id, VaccineNewUpdateDTO vaccineDTO);

    void deleteVaccine(Long id);
}
