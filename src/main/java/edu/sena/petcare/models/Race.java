package edu.sena.petcare.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString(exclude = { "mascotas", "especie" })
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "race")
public class Race {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, length = 50)
    @EqualsAndHashCode.Include
    private String name;

    // relacion OneToMany con Specie
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_specie")
    private Specie especie;

    // relacion OneToMany con Pet
    @OneToMany(mappedBy = "raza", fetch = FetchType.LAZY)
    private List<Pet> mascotas;
}
