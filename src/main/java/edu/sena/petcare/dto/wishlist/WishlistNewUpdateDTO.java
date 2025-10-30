package edu.sena.petcare.dto.wishlist;

import jakarta.validation.constraints.NotNull;
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

    private List<Long> productIds;
}