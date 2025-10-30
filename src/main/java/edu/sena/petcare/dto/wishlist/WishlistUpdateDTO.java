package edu.sena.petcare.dto.wishlist;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishlistUpdateDTO {

    // El único campo actualizable.

    @NotNull(message = "La lista de productos no puede ser nula, envíe una lista vacía si desea limpiarla.")
    private List<Long> productIds;
}