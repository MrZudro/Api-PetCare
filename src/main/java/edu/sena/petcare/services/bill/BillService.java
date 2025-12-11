package edu.sena.petcare.services.bill;

import java.util.List;

import edu.sena.petcare.dto.bill.BillReadDTO;

public interface BillService {
    List<BillReadDTO> getAll();

    BillReadDTO getById(Long id);

    BillReadDTO createOrder(edu.sena.petcare.dto.bill.CheckoutDTO checkoutDTO);

    List<BillReadDTO> getByCustomerId(Long customerId);
}
