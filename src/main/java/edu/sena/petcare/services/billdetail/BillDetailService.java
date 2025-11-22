package edu.sena.petcare.services.billdetail;

import java.util.List;

import edu.sena.petcare.dto.billdetail.BillDetailReadDTO;

public interface BillDetailService {
    List<BillDetailReadDTO> getAll();

    BillDetailReadDTO getById(Long id);
}
