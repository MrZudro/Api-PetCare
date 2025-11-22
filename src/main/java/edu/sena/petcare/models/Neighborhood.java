package edu.sena.petcare.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString(exclude = { "users", "locality" })
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "neighborhood")
public class Neighborhood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, length = 255)
    @EqualsAndHashCode.Include
    private String name;

    // relacion OneToMany con Locality
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_locality")
    private Locality locality;

    // relacion OneToMany con User
    @OneToMany(mappedBy = "barrioCliente", fetch = FetchType.LAZY)
    private List<User> users;
}
