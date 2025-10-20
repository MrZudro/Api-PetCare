package edu.sena.petcare.models;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"user", "products"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "wishlist")
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;

    //relacion OneToMany con user
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    //relacion ManyToMany con Product
    @ManyToMany(mappedBy = "wishlists")
    private List<Product> products;
}
