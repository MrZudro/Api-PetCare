package edu.sena.petcare.dto.methodpaymentcustomer;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MethodPaymentCustomerReadDTO {
    private Long id;
    private String brand; // Visa, Mastercard, Amex, Discover
    private Integer lastFourDigits;
    private String expirationDate; // Formato: MM/YY
    private Boolean isDefault;
    private LocalDateTime creationDate;
    private Long idUser;
}
