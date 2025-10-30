package edu.sena.petcare.models;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"condition", "pet"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "pet_conditions")
public class PetConditions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pet",
                nullable = false,
                foreignKey = @ForeignKey(name = "FK_pet_conditions_pet"))
    private Pet pet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_condition",
                nullable = false,
                foreignKey = @ForeignKey(name = "FK_pet_conditions_condition"))
    private Conditions condition;

}
