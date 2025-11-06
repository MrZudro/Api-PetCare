package edu.sena.petcare.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString(exclude = {"razas"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "specie")
public class Specie {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, length = 30)
    @EqualsAndHashCode.Include
    private String name;

    //relacion OneToMany con Race
    @OneToMany(mappedBy = "especie")
    private List<Race> razas;


}
