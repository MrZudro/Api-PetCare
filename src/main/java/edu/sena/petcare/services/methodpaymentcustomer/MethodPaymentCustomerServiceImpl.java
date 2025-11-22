package edu.sena.petcare.services.methodpaymentcustomer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import edu.sena.petcare.dto.methodpaymentcustomer.MethodPaymentCustomerReadDTO;
import edu.sena.petcare.mapper.MethodPaymentCustomerMapper;
import edu.sena.petcare.repositories.MethodPaymentCustomerRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MethodPaymentCustomerServiceImpl implements MethodPaymentCustomerService {

    private final MethodPaymentCustomerRepository methodPaymentCustomerRepository;
    private final MethodPaymentCustomerMapper methodPaymentCustomerMapper;

    @Override
    public List<MethodPaymentCustomerReadDTO> getAll() {
        return methodPaymentCustomerRepository.findAll()
                .stream()
                .map(methodPaymentCustomerMapper::toReadDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MethodPaymentCustomerReadDTO getById(Long id) {
        return methodPaymentCustomerRepository.findById(id)
                .map(methodPaymentCustomerMapper::toReadDTO)
                .orElseThrow(() -> new RuntimeException("MethodPaymentCustomer not found with id: " + id));
    }
}
