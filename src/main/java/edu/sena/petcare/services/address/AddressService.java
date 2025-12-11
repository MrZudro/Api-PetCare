package edu.sena.petcare.services.address;

import edu.sena.petcare.dto.address.AddressNewDTO;
import edu.sena.petcare.dto.address.AddressReadDTO;
import edu.sena.petcare.dto.address.AddressUpdateDTO;

import java.util.List;

public interface AddressService {

    AddressReadDTO create(Long customerId, AddressNewDTO dto);

    List<AddressReadDTO> findByCustomerId(Long customerId);

    AddressReadDTO findById(Long id);

    AddressReadDTO update(Long id, AddressUpdateDTO dto);

    void delete(Long id);

    AddressReadDTO setAsDefault(Long customerId, Long addressId);
}
