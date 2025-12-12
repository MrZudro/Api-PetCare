package edu.sena.petcare.mapper;

import org.springframework.stereotype.Component;
import edu.sena.petcare.dto.wishlist.WishlistReadDTO;
import edu.sena.petcare.models.Wishlist;
import edu.sena.petcare.utility.ListaMappeo;
import java.util.Collections;
import java.util.List;

@Component
public class WishlistMapper {

    private final ProductMapper productMapper;

    public WishlistMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public WishlistReadDTO toDto(Wishlist entity) {
        if (entity == null) {
            return null;
        }
        WishlistReadDTO dto = new WishlistReadDTO();
        dto.setId(entity.getId());
        dto.setCreateDate(entity.getCreateDate() != null ? entity.getCreateDate().toString() : null);
        dto.setUserId(entity.getUser() != null ? entity.getUser().getId() : null);

        var products = entity.getProducts() == null
                ? Collections.<edu.sena.petcare.dto.product.ProductReadDTO>emptyList()
                : entity.getProducts().stream()
                        .map(productMapper::toDto)
                        .toList();
        dto.setProducts(products);

        return dto;
    }

    public List<WishlistReadDTO> toDtoList(List<Wishlist> entities) {
        return ListaMappeo.toDtoList(entities, this::toDto);
    }
}