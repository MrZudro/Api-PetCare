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
@ToString(exclude = {"billDetails", "wishlists", "categories"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table( name = "product",
        uniqueConstraints = @UniqueConstraint(name = "uk_product", columnNames = {"name",  "brand"})
)
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

    @Column(length = 100, nullable = false)
    private String sku;

    @Enumerated(EnumType.STRING)
    private StatusService isActive;

    //relacion OneToMany con BillDetail
    @OneToMany(mappedBy = "product")
    private List<BillDetail> billDetails;

    //relacion ManyToMany con Wishlists
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable( name = "wishlists_product",
                joinColumns = @JoinColumn(name="id_product"),
                inverseJoinColumns = @JoinColumn(name="id_wishlists")
    )
    private List<Wishlist> wishlists;

    //relacion ManyToMany con categories
    @ManyToMany
    @JoinTable(
        name = "producto_subcategoria",
        joinColumns = @JoinColumn(name = "producto_id"),
        inverseJoinColumns = @JoinColumn(name = "subcategoria_id")
    )
    private List<Category> categories;
}
