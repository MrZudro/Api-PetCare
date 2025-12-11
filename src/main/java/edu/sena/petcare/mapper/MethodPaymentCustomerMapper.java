package edu.sena.petcare.mapper;

import org.springframework.stereotype.Component;

import edu.sena.petcare.dto.methodpaymentcustomer.MethodPaymentCustomerNewUpdateDTO;
import edu.sena.petcare.dto.methodpaymentcustomer.MethodPaymentCustomerReadDTO;
import edu.sena.petcare.models.MethodPaymentCustomer;

@Component
public class MethodPaymentCustomerMapper {

    /**
     * Convierte una entidad MethodPaymentCustomer a DTO de lectura
     */
    public MethodPaymentCustomerReadDTO toDto(MethodPaymentCustomer method) {
        if (method == null) {
            return null;
        }

        MethodPaymentCustomerReadDTO dto = new MethodPaymentCustomerReadDTO();
        dto.setId(method.getId());
        dto.setBrand(method.getBrand());
        dto.setLastFourDigits(method.getLastFourDigits());
        dto.setExpirationDate(method.getExpirationDate()); // Ya está en formato MM/YY
        dto.setIsDefault(method.getIsDefault());
        dto.setCreationDate(method.getCreationDate());

        // Mapear el ID del usuario si existe
        if (method.getUser() != null) {
            dto.setIdUser(method.getUser().getId());
        }

        return dto;
    }

    /**
     * Convierte un DTO de creación/actualización a entidad MethodPaymentCustomer
     * Nota: El usuario debe ser asignado externamente en el servicio
     */
    public MethodPaymentCustomer toEntity(MethodPaymentCustomerNewUpdateDTO dto) {
        if (dto == null) {
            return null;
        }

        MethodPaymentCustomer method = new MethodPaymentCustomer();
        method.setTokenPaymentMethod(dto.getTokenPaymentMethod());
        method.setTokenClientGateway(dto.getTokenClientGateway());
        method.setBrand(dto.getBrand());
        method.setLastFourDigits(dto.getLastFourDigits());
        method.setExpirationDate(dto.getExpirationDate());
        method.setIsDefault(dto.getIsDefault() != null ? dto.getIsDefault() : false);
        method.setCreationDate(java.time.LocalDateTime.now());

        return method;
    }

    /**
     * Actualiza una entidad existente con datos del DTO
     */
    public void updateEntity(MethodPaymentCustomerNewUpdateDTO dto, MethodPaymentCustomer method) {
        if (dto == null || method == null) {
            return;
        }

        if (dto.getTokenPaymentMethod() != null) {
            method.setTokenPaymentMethod(dto.getTokenPaymentMethod());
        }
        if (dto.getTokenClientGateway() != null) {
            method.setTokenClientGateway(dto.getTokenClientGateway());
        }
        if (dto.getBrand() != null) {
            method.setBrand(dto.getBrand());
        }
        if (dto.getLastFourDigits() != null) {
            method.setLastFourDigits(dto.getLastFourDigits());
        }
        if (dto.getExpirationDate() != null) {
            method.setExpirationDate(dto.getExpirationDate());
        }
        if (dto.getIsDefault() != null) {
            method.setIsDefault(dto.getIsDefault());
        }
    }
}
