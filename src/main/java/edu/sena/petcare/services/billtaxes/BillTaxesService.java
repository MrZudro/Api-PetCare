package edu.sena.petcare.services.billtaxes;

import java.util.List;

import edu.sena.petcare.dto.billtaxes.BillTaxesReadDTO;

public interface BillTaxesService {
    List<BillTaxesReadDTO> getAll();

    BillTaxesReadDTO getById(Long id);
}
