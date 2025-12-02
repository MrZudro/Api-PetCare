package edu.sena.petcare.models;

import java.time.LocalDateTime;

import edu.sena.petcare.models.enums.ConsultationStatus;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = { "veterinaryClinic", "employee", "pet" })
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "consultation")
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    // Relación con el empleado (Veterinario) que realiza la consulta
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_employee", nullable = false)
    private Employee employee;

    // Relación con la clínica donde se realiza la consulta
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_veterinary", nullable = false)
    private VeterinaryClinic veterinaryClinic;

    // Relación con la mascota consultada
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pet", nullable = false)
    private Pet pet;

    @Column(name = "consultation_date_time", nullable = false)
    private LocalDateTime consultationDateTime;

    @Column(name = "symptoms", length = 1000, nullable = false)
    private String symptoms; // Síntomas reportados

    @Column(name = "diagnosis", length = 1000, nullable = false)
    private String diagnosis; // Diagnóstico del veterinario

    @Column(name = "treatment", length = 1000, nullable = false)
    private String treatment; // Tratamiento recomendado

    @Column(name = "notes", length = 1000)
    private String notes; // Notas adicionales

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 50)
    private ConsultationStatus status; // Estado (COMPLETED, PENDING_REVIEW, etc.)

    @Column(length = 500)
    private String reason; // Motivo de la consulta

    @Column(length = 20)
    private String weight; // Peso de la mascota en la consulta

    @Column(length = 20)
    private String temperature; // Temperatura de la mascota

    @Column(name = "physical_examination", length = 1000)
    private String physicalExamination; // Examen físico
}
