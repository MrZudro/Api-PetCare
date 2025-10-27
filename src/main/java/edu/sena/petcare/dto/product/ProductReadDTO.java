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

    private String brand;

    private BigDecimal price;

    private List<Long> subcategoriesIds;
}
