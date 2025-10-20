package edu.sena.petcare.models;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString(exclude = "barrios")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "locality")
public class Locality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, length = 255)
    @EqualsAndHashCode.Include
    private String name;

    //relacion OneToMany con Neighborhood
    @OneToMany(mappedBy = "locality")
    private List<Neighborhood> barrios;
}
