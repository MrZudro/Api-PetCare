package edu.sena.petcare.services.Vaccine;

import edu.sena.petcare.dto.Vaccine.VaccineCreateDTO;
import edu.sena.petcare.dto.Vaccine.VaccineReadDTO;
import edu.sena.petcare.dto.Vaccine.VaccineUpdateDTO;
import java.util.List;


public interface VaccineService {
    VaccineReadDTO create(VaccineCreateDTO dto);
    VaccineReadDTO update(Long id, VaccineUpdateDTO dto);
    void delete(Long id);
    VaccineReadDTO getById(Long id);
    List<VaccineReadDTO> getAll();
}
