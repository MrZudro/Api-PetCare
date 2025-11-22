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
    private String cardNumber;
    private String cardType;
    private String bankName;
    private String cvv;
    private String cardHolderName;
    private LocalDateTime expirationDate;
    private LocalDateTime creationDate;
}
