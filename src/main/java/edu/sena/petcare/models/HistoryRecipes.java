package edu.sena.petcare.models;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = { "pet", "recipe", "employee", "consultation" })
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "history_recipes")
public class HistoryRecipes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, name = "prescribed_date")
    private LocalDate prescribedDate;

    @Column(nullable = false, name = "valid_until")
    private LocalDate validUntil;

    @Column(length = 100)
    private String dosage;

    @Column(length = 100)
    private String frequency;

    @Column(length = 1000)
    private String notes;

    // Relación ManyToOne con Pet
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pet", nullable = false)
    private Pet pet;

    // Relación ManyToOne con Recipe
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_recipe", nullable = false)
    private Recipe recipe;

    // Relación ManyToOne con Employee (veterinario que prescribe)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_employee", nullable = false)
    private Employee employee;

    // Relación ManyToOne con Consultation (opcional - la consulta donde se
    // prescribió)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_consultation")
    private Consultation consultation;
}
