package edu.sena.petcare.dto.product;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductReadDTO {

    private Long id;

    private String name;

    private String picture;

    private String imageUrl; // Frontend expects this

    private String brand;

    private String description;

    private BigDecimal price;

    private Integer stock;

    private String subcategories; // First subcategory name (frontend expects string)

    private List<Long> subcategoriesIds;
}
