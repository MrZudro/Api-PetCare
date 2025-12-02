package edu.sena.petcare.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = { "vaccine", "pet", "employee", "veterinaryClinic" })
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "vaccination_history")
public class VaccinationHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, name = "application_date")
    private LocalDate applicationDate;

    @Column(name = "next_due_date")
    private LocalDate nextDueDate;

    @Column(length = 50, name = "lot_number")
    private String lotNumber;

    @Column(length = 1000)
    private String observations;

    @Column(length = 300)
    private String certificate;

    // Relación ManyToOne con Vaccine
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vaccine", nullable = false)
    private Vaccine vaccine;

    // Relación ManyToOne con Pet
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pet", nullable = false)
    private Pet pet;

    // Relación ManyToOne con Employee (veterinario que aplicó)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_employee", nullable = false)
    private Employee employee;

    // Relación ManyToOne con VeterinaryClinic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_clinic", nullable = false)
    private VeterinaryClinic veterinaryClinic;
}
