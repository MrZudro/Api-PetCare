package edu.sena.petcare.models;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = { "user", "raza" })
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(length = 300)
    private String imageUrl;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Column(length = 20, unique = true)
    @EqualsAndHashCode.Include
    private String microchip;

    @Column(length = 15)
    private String color;

    @Column(length = 10)
    private String weight;

    @Column(nullable = false, length = 10)
    private String gender;

    // relacion OneToMany con Race
    @ManyToOne
    @JoinColumn(name = "id_race")
    private Race raza;

    // relacion OneToMany con User
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    // relacion OneToMany con PetConditions
    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PetConditions> petConditions;
}
