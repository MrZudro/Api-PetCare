package edu.sena.petcare.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = { "vaccinationHistories" })
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "vaccine")
public class Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(length = 100)
    private String manufacturer;

    @Column(name = "recommended_age_months")
    private Integer recommendedAgeMonths;

    @Column(name = "validity_months")
    private Integer validityMonths;

    // Relación OneToMany con VaccinationHistory
    @OneToMany(mappedBy = "vaccine", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<VaccinationHistory> vaccinationHistories;
}
