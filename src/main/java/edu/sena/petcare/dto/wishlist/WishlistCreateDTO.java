package edu.sena.petcare.dto.wishlist;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishlistCreateDTO {

    @NotNull(message = "El ID del usuario no puede ser nulo")
    private Long userId;

    // YA NO ES @NotEmpty. Puede ser nula o vacía al crear.
    private List<Long> productIds;
}