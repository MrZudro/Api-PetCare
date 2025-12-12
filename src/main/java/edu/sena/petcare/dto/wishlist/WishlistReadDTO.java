
package edu.sena.petcare.dto.wishlist;

import edu.sena.petcare.dto.product.ProductReadDTO;
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

    private List<ProductReadDTO> products; // Productos completos en la lista

}