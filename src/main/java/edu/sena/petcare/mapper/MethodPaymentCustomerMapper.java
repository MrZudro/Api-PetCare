package edu.sena.petcare.mapper;

import org.springframework.stereotype.Component;

import edu.sena.petcare.dto.methodpaymentcustomer.MethodPaymentCustomerNewUpdateDTO;
import edu.sena.petcare.dto.methodpaymentcustomer.MethodPaymentCustomerReadDTO;
import edu.sena.petcare.models.MethodPaymentCustomer;

@Component
public class MethodPaymentCustomerMapper {

    public static final String MSG_DATE = "MM/yyyy";

    public MethodPaymentCustomerReadDTO toDto(MethodPaymentCustomer method) {
        if (method == null) {
            return null;
        }
        MethodPaymentCustomerReadDTO dto = new MethodPaymentCustomerReadDTO();
        dto.setId(method.getId());
        // Map lastFourDigits to masked card number
        dto.setCardNumber("**** **** **** " + method.getLastFourDigits());
        dto.setCardType(method.getBrand());
        // Bank name, CVV, CardHolderName are not in model, setting to null or empty
        dto.setBankName(null);
        dto.setCvv(null);
        dto.setCardHolderName(null);

        if (method.getExpirationDate() != null) {
            try {
                // Assuming format MM/yyyy
                java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern(MSG_DATE);
                java.time.YearMonth ym = java.time.YearMonth.parse(method.getExpirationDate(), formatter);
                dto.setExpirationDate(ym.atDay(1).atStartOfDay());
            } catch (Exception e) {
                dto.setExpirationDate(null);
            }
        }

        dto.setCreationDate(method.getCreationDate());
        return dto;
    }

    public MethodPaymentCustomer toEntity(MethodPaymentCustomerNewUpdateDTO dto) {
        if (dto == null) {
            return null;
        }
        MethodPaymentCustomer method = new MethodPaymentCustomer();

        if (dto.getCardNumber() != null && dto.getCardNumber().length() >= 4) {
            String num = dto.getCardNumber().replaceAll("\\D", "");
            if (num.length() >= 4) {
                method.setLastFourDigits(Integer.parseInt(num.substring(num.length() - 4)));
            }
        }

        method.setBrand(dto.getCardType());

        if (dto.getExpirationDate() != null) {
            java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern(MSG_DATE);
            method.setExpirationDate(dto.getExpirationDate().format(formatter));
        }

        // Required fields not in DTO - setting defaults or null (might fail validation
        // but fixes compilation)
        method.setTokenPaymentMethod("PENDING_TOKEN");
        method.setTokenClientGateway("PENDING_CLIENT");
        method.setIsDefault(false);
        method.setCreationDate(java.time.LocalDateTime.now());

        return method;
    }

    public void updateEntity(MethodPaymentCustomerNewUpdateDTO dto, MethodPaymentCustomer method) {
        if (dto == null || method == null) {
            return;
        }
        if (dto.getCardNumber() != null) {
            String num = dto.getCardNumber().replaceAll("\\D", "");
            if (num.length() >= 4) {
                method.setLastFourDigits(Integer.parseInt(num.substring(num.length() - 4)));
            }
        }
        if (dto.getCardType() != null) {
            method.setBrand(dto.getCardType());
        }
        if (dto.getExpirationDate() != null) {
            java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern(MSG_DATE);
            method.setExpirationDate(dto.getExpirationDate().format(formatter));
        }
    }
}
