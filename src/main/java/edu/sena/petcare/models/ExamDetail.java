package edu.sena.petcare.models;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"exam", "pet", "employee"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "exam_details")
public class ExamDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "exam_date", nullable = false)
    private LocalDateTime examDate;

    @Column(columnDefinition = "TEXT")
    private String results;

    // Relación ManyToOne con Exam (el tipo de examen)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_id", nullable = false)
    private Exam exam;

    // Relación ManyToOne con Pet (la mascota a la que se le hizo)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;

    // Relación ManyToOne con employee (el veterinario que lo hizo)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vet_id", nullable = false)
    private Employee employee;
}