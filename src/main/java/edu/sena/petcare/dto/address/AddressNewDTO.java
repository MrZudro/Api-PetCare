package edu.sena.petcare.dto.address;

import edu.sena.petcare.models.enums.AddressType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressNewDTO {

    @NotBlank(message = "La dirección es obligatoria")
    private String addressLine;

    private String additionalInfo;

    private String deliveryNotes;

    @NotNull(message = "El tipo de dirección es obligatorio")
    private AddressType addressType;

    private Boolean isDefault;

    @NotNull(message = "El barrio es obligatorio")
    private Long neighborhoodId;
}
