package edu.sena.petcare.services.customer;

import java.util.List;

import edu.sena.petcare.dto.customer.CustomerNewUpdateDTO;
import edu.sena.petcare.dto.customer.CustomerReadDTO;

public interface CustomerService {
    CustomerReadDTO create(CustomerNewUpdateDTO dto);

    List<CustomerReadDTO> findAll();

    CustomerReadDTO findById(Long id);

    CustomerReadDTO update(Long id, CustomerNewUpdateDTO dto);

    void delete(Long id);

    void changePassword(String email, String currentPassword, String newPassword);
}
