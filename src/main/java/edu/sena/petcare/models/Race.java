package edu.sena.petcare.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString(exclude = {"mascotas", "especie"})
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

    //relacion OneToMany con Specie
    @ManyToOne
    @JoinColumn(name = "id_specie")
    private Species especie;

    //relacion OneToMany con Pet
    @OneToMany(mappedBy = "raza")
    private List<Pet> mascotas;
}
