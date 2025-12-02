package edu.sena.petcare.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = { "historyRecipes" })
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "recipe")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, length = 150, name = "medication_name")
    private String medicationName;

    @Column(length = 500)
    private String description;

    @Column(length = 1000, name = "dosage_instructions")
    private String dosageInstructions;

    @Column(length = 500, name = "side_effects")
    private String sideEffects;

    @Column(length = 500)
    private String contraindications;

    // Relación OneToMany con HistoryRecipes
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HistoryRecipes> historyRecipes;
}
