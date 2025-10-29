package edu.sena.petcare.dto.wishlist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishlistReadDTO {

    private Long id;

    private String createDate;

    private Long userId;

    private List<Long> productIds; // IDs de los productos en la lista

}