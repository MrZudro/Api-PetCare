package edu.sena.petcare.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"product", "subcategory"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table( name = "product_subcategory",
        uniqueConstraints = @UniqueConstraint(
            name = "UK_product_subcategory",
            columnNames = {"id_product",  "id_subcategory"}
        ))
public class ProductSubcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //relacion de conexion tabla intermedia como ManyToOne de product
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_product",
                nullable = false,
                foreignKey = @ForeignKey(name = "FK_product_subcategory_product"))
    private Product product; 
    
    //relacion de conexion tabla intermedia como ManyToOne de subcategory
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_subcategory",
                nullable = false,
                foreignKey = @ForeignKey(name = "FK_product_subcategory_subcategory"))
    private Subcategory subcategory;
}
