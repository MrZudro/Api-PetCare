package edu.sena.petcare.models;

import java.math.BigDecimal;
import java.util.List;

import edu.sena.petcare.models.enums.StatusService;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = { "billDetails", "wishlists", "productSubcategories" })
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "product", uniqueConstraints = @UniqueConstraint(name = "uk_product", columnNames = { "name", "brand" }))
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(length = 100, nullable = false)
    @EqualsAndHashCode.Include
    private String name;

    @Column(length = 255, nullable = false)
    private String picture;

    @Column(precision = 19, scale = 2, nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer stock;

    @Column(length = 100, nullable = false)
    @EqualsAndHashCode.Include
    private String brand;

    @Column(length = 500, nullable = false)
    private String description;

    @Column(length = 100, nullable = false, unique = true)
    private String sku;

    @Enumerated(EnumType.STRING)
    private StatusService isActive;

    // relacion OneToMany con BillDetail
    @OneToMany(mappedBy = "product")
    private List<BillDetail> billDetails;

    // relacion ManyToMany con Wishlists
    @ManyToMany(mappedBy = "products")
    private List<Wishlist> wishlists;

    // relacion OneToMany con ProductSubcategory
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductSubcategory> productSubcategories;
}
