package edu.sena.petcare.dto.product;

import java.math.BigDecimal;
import java.util.List;

import edu.sena.petcare.models.enums.StatusService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductNewUpdateDTO {

    private String name;
    private String picture;
    private String brand;
    private String description;
    private String sku;
    private BigDecimal price;
    private Integer stock;
    private List<Long> subcategoriaIds;
    private StatusService isActive;
}
