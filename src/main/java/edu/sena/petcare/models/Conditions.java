package edu.sena.petcare.models;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "conditions")
public class Conditions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false, length = 255)
    private String description;

    @Column(nullable = false, length = 255)
    private String icon;

    //relacion OneToMany con PetConditions
    @OneToMany(mappedBy = "condition", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PetConditions> petConditions;
}
