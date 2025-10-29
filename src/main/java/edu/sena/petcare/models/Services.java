package edu.sena.petcare.models;

import edu.sena.petcare.models.enums.StatusService;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString(exclude = {"veterinaryClinicServices"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "services")
public class Services {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Enumerated(EnumType.STRING)
    private StatusService status;

    @Column(nullable = false, length = 100)
    @EqualsAndHashCode.Include
    private String name;

    @Column(nullable = false, length =255)
    private String description;

    @Column(columnDefinition = "TEXT")
    private String picture;

    //relacion OneToMany con VeterinaryClinicService
    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VeterinaryClinicService> veterinaryClinicServices;
}
