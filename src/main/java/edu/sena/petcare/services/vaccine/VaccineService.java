package edu.sena.petcare.services.vaccine;

import edu.sena.petcare.dto.vaccine.VaccineCreateDTO;
import edu.sena.petcare.dto.vaccine.VaccineReadDTO;
import edu.sena.petcare.dto.vaccine.VaccineUpdateDTO;
import java.util.List;

public interface VaccineService {
    VaccineReadDTO create(VaccineCreateDTO dto);
    List<VaccineReadDTO> findAll();
    VaccineReadDTO findById(Long id);
    VaccineReadDTO update(Long id, VaccineUpdateDTO dto);
    void deactivate(Long id);
}
