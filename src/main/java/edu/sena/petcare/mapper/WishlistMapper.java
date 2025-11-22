package edu.sena.petcare.mapper;

import org.springframework.stereotype.Component;
import edu.sena.petcare.dto.wishlist.WishlistReadDTO;
import edu.sena.petcare.models.Product;
import edu.sena.petcare.models.Wishlist;
import edu.sena.petcare.utility.ListaMappeo;
import java.util.Collections;
import java.util.List;

@Component
public class WishlistMapper {

    public WishlistReadDTO toDto(Wishlist entity) {
        if (entity == null) {
            return null;
        }
        WishlistReadDTO dto = new WishlistReadDTO();
        dto.setId(entity.getId());
        dto.setCreateDate(entity.getCreateDate() != null ? entity.getCreateDate().toString() : null);
        dto.setUserId(entity.getUser() != null ? entity.getUser().getId() : null);

        List<Long> productIds = entity.getProducts() == null
                ? Collections.emptyList()
                : entity.getProducts().stream()
                        .map(Product::getId)
                        .toList();
        dto.setProductIds(productIds);

        return dto;
    }

    public List<WishlistReadDTO> toDtoList(List<Wishlist> entities) {
        return ListaMappeo.toDtoList(entities, this::toDto);
    }
}