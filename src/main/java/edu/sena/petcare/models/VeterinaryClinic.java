package edu.sena.petcare.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = {"veterinaryClinicServices", "facturas", "documentTypeVeterinary"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "veterinary_clinic",
        uniqueConstraints = @UniqueConstraint(name = "uk_veterinary",  columnNames = {"id_document_type", "document_number"})
)
public class VeterinaryClinic {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String address;

    @Column(nullable = false, length = 12)
    private String phone;

    @Column(nullable = false, length = 255)
    private String email;   

    @Column(name = "document_number", nullable = false, length = 100)
    @EqualsAndHashCode.Include
    private String documentNumber;


    //relacion OneToMany con VeterinaryClinic
    @ManyToOne
    @JoinColumn(name = "id_document_type")
    private DocumentType documentTypeVeterinary;

    //relacion OneToMany con VeterinaryClinicService
    @OneToMany(mappedBy = "veterinaryClinic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VeterinaryClinicService> veterinaryClinicServices;

    //relacion OneToMany con Bill
    @OneToMany(mappedBy = "veterinaryClinic")
    private List<Bill> facturas;
}
