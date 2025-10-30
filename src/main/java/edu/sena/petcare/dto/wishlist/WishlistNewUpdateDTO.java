package edu.sena.petcare.dto.wishlist;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishlistNewUpdateDTO {

    @NotNull(message = "El ID del usuario no puede ser nulo")
    private Long userId;

    @NotEmpty(message = "La lista de productos no puede estar vacía")
    private List<Long> productIds;
}