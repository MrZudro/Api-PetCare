package edu.sena.petcare.mapper;

import edu.sena.petcare.dto.wishlist.WishlistReadDTO;
import edu.sena.petcare.models.Product;
import edu.sena.petcare.models.Wishlist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface WishlistMapper {

    // --- De Entidad a DTO ---

    @Mappings({
            @Mapping(source = "user.id", target = "userId"),
            @Mapping(target = "productIds", expression = "java(mapProductsToProductIds(wishlist.getProducts()))")
    })
    WishlistReadDTO toReadDTO(Wishlist wishlist);

    // Método auxiliar para mapear la lista de entidades Product a IDs
    default List<Long> mapProductsToProductIds(List<Product> products) {
        if (products == null) {
            return java.util.Collections.emptyList();
        }
        return products.stream()
                .map(Product::getId)
                .collect(Collectors.toList());
    }

}