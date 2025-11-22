
package edu.sena.petcare.mapper;

import org.springframework.stereotype.Component;
import edu.sena.petcare.dto.product.ProductNewUpdateDTO;
import edu.sena.petcare.dto.product.ProductReadDTO;
import edu.sena.petcare.models.Product;
import edu.sena.petcare.models.enums.StatusService;
import edu.sena.petcare.utility.ListaMappeo;
import java.util.Collections;
import java.util.List;

@Component
public class ProductMapper {

    public ProductReadDTO toDto(Product entity) {
        if (entity == null) {
            return null;
        }
        ProductReadDTO dto = new ProductReadDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPicture(entity.getPicture());
        dto.setBrand(entity.getBrand());
        dto.setPrice(entity.getPrice());

        List<Long> subcategoryIds = entity.getProductSubcategories() == null
                ? Collections.emptyList()
                : entity.getProductSubcategories().stream()
                        .map(ps -> ps.getSubcategory().getId())
                        .toList();
        dto.setSubcategoriesIds(subcategoryIds);

        return dto;
    }

    public Product toEntity(ProductNewUpdateDTO dto) {
        if (dto == null) {
            return null;
        }
        Product entity = new Product();
        entity.setName(dto.getName());
        entity.setPicture(dto.getPicture());
        entity.setBrand(dto.getBrand());
        entity.setDescription(dto.getDescription());
        entity.setSku(dto.getSku());
        entity.setPrice(dto.getPrice());
        entity.setStock(dto.getStock());
        entity.setIsActive(StatusService.ACTIVE);
        // Subcategories handled in Service
        return entity;
    }

    public void updateEntity(ProductNewUpdateDTO dto, Product entity) {
        if (dto == null || entity == null) {
            return;
        }
        if (dto.getName() != null)
            entity.setName(dto.getName());
        if (dto.getPicture() != null)
            entity.setPicture(dto.getPicture());
        if (dto.getBrand() != null)
            entity.setBrand(dto.getBrand());
        if (dto.getDescription() != null)
            entity.setDescription(dto.getDescription());
        if (dto.getSku() != null)
            entity.setSku(dto.getSku());
        if (dto.getPrice() != null)
            entity.setPrice(dto.getPrice());
        if (dto.getStock() != null)
            entity.setStock(dto.getStock());
        if (dto.getIsActive() != null)
            entity.setIsActive(dto.getIsActive());
        // Subcategories handled in Service
    }

    public ProductNewUpdateDTO toUpdateDto(Product entity) {
        if (entity == null) {
            return null;
        }
        ProductNewUpdateDTO dto = new ProductNewUpdateDTO();
        dto.setName(entity.getName());
        dto.setPicture(entity.getPicture());
        dto.setBrand(entity.getBrand());
        dto.setDescription(entity.getDescription());
        dto.setSku(entity.getSku());
        dto.setPrice(entity.getPrice());
        dto.setStock(entity.getStock());
        dto.setIsActive(entity.getIsActive());

        List<Long> subcategoryIds = entity.getProductSubcategories() == null
                ? Collections.emptyList()
                : entity.getProductSubcategories().stream()
                        .map(ps -> ps.getSubcategory().getId())
                        .toList();
        dto.setSubcategoriaIds(subcategoryIds);

        return dto;
    }

    public List<ProductReadDTO> toDtoList(List<Product> entities) {
        return ListaMappeo.toDtoList(entities, this::toDto);
    }
}