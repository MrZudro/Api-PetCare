package edu.sena.petcare.dto.methodpaymentcustomer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MethodPaymentCustomerNewUpdateDTO {

    @NotBlank(message = "El token del método de pago es requerido")
    @Size(max = 255, message = "El token no puede exceder 255 caracteres")
    private String tokenPaymentMethod;

    @NotBlank(message = "El token del cliente en la pasarela es requerido")
    @Size(max = 255, message = "El token del cliente no puede exceder 255 caracteres")
    private String tokenClientGateway;

    @NotBlank(message = "La marca de la tarjeta es requerida")
    @Size(max = 50, message = "La marca no puede exceder 50 caracteres")
    private String brand; // Visa, Mastercard, Amex, Discover

    @NotNull(message = "Los últimos 4 dígitos son requeridos")
    private Integer lastFourDigits;

    @NotBlank(message = "La fecha de expiración es requerida")
    @Pattern(regexp = "^(0[1-9]|1[0-2])/[0-9]{2}$", message = "Formato de fecha inválido. Use MM/YY")
    @Size(max = 7, message = "La fecha de expiración no puede exceder 7 caracteres")
    private String expirationDate; // Formato: MM/YY

    private Boolean isDefault = false;
}
