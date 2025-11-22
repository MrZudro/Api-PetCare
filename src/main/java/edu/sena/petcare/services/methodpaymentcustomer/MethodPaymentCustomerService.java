package edu.sena.petcare.services.methodpaymentcustomer;

import java.util.List;

import edu.sena.petcare.dto.methodpaymentcustomer.MethodPaymentCustomerReadDTO;

public interface MethodPaymentCustomerService {
    List<MethodPaymentCustomerReadDTO> getAll();

    MethodPaymentCustomerReadDTO getById(Long id);
}
