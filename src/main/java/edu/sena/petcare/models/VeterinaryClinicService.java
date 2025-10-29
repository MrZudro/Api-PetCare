package edu.sena.petcare.models;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"veterinaryClinic", "service"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "veterinary_clinic_service",
        uniqueConstraints = @UniqueConstraint(
                name = "UK_veterinary_clinic_service",
                columnNames = {"id_veterinary_clinic", "id_service"}
        ))
public class VeterinaryClinicService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_veterinary_clinic",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_veterinary_clinic_service_veterinary_clinic"))
    private VeterinaryClinic veterinaryClinic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_service",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_veterinary_clinic_service_service"))
    private Services service;
}
