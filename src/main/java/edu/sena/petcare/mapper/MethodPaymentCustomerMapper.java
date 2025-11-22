package edu.sena.petcare.mapper;

import org.springframework.stereotype.Component;

import edu.sena.petcare.dto.methodpaymentcustomer.MethodPaymentCustomerNewUpdateDTO;
import edu.sena.petcare.dto.methodpaymentcustomer.MethodPaymentCustomerReadDTO;
import edu.sena.petcare.models.MethodPaymentCustomer;

@Component
public class MethodPaymentCustomerMapper {

    public MethodPaymentCustomerReadDTO toDto(MethodPaymentCustomer method) {
        if (method == null) {
            return null;
        }
        MethodPaymentCustomerReadDTO dto = new MethodPaymentCustomerReadDTO();
        dto.setId(method.getId());
        dto.setCardNumber(method.getCardNumber());
        dto.setCardType(method.getCardType());
        dto.setBankName(method.getBankName());
        dto.setCvv(method.getCvv());
        dto.setCardHolderName(method.getCardHolderName());
        dto.setExpirationDate(method.getExpirationDate());
        dto.setCreationDate(method.getCreationDate());
        return dto;
    }

    public MethodPaymentCustomer toEntity(MethodPaymentCustomerNewUpdateDTO dto) {
        if (dto == null) {
            return null;
        }
        MethodPaymentCustomer method = new MethodPaymentCustomer();
        method.setCardNumber(dto.getCardNumber());
        method.setCardType(dto.getCardType());
        method.setBankName(dto.getBankName());
        method.setCvv(dto.getCvv());
        method.setCardHolderName(dto.getCardHolderName());
        method.setExpirationDate(dto.getExpirationDate());
        return method;
    }

    public void updateEntity(MethodPaymentCustomerNewUpdateDTO dto, MethodPaymentCustomer method) {
        if (dto == null || method == null) {
            return;
        }
        method.setCardNumber(dto.getCardNumber());
        method.setCardType(dto.getCardType());
        method.setBankName(dto.getBankName());
        method.setCvv(dto.getCvv());
        method.setCardHolderName(dto.getCardHolderName());
        method.setExpirationDate(dto.getExpirationDate());
    }
}
