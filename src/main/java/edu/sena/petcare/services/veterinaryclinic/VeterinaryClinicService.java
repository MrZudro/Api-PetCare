package edu.sena.petcare.services.veterinaryclinic;

import java.util.List;

import edu.sena.petcare.dto.veterinaryclinic.VeterinaryClinicReadDTO;

public interface VeterinaryClinicService {
    List<VeterinaryClinicReadDTO> getAll();

    VeterinaryClinicReadDTO getById(Long id);
}
