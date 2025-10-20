package edu.sena.petcare.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString(exclude = {"veterinaryClinics"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "services")
public class Services {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private Boolean status;

    @Column(nullable = false, length = 100)
    @EqualsAndHashCode.Include
    private String name;

    @Column(nullable = false, length =255)
    private String description;

    @Column(columnDefinition = "TEXT")
    private String picture;

    //relacion de ManyToMany con VeterinaryClinic
    @ManyToMany
    @JoinTable(
        name = "veterinary_clinic_services",
        joinColumns = @JoinColumn(name = "id_service"),
        inverseJoinColumns = @JoinColumn(name = "id_veterinary_clinic")
    )
    private List<VeterinaryClinic> veterinaryClinics;
}
