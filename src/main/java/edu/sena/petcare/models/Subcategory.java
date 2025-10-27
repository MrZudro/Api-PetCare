package edu.sena.petcare.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"categoria", "productos"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "subcategory")
public class Subcategory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    // Relación Many-to-One con Categoria
    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Category categoria;
    
    // Relación Many-to-Many con Producto (el lado inverso)
    @ManyToMany(mappedBy = "subcategorias")
    private List<Product> productos;
}
