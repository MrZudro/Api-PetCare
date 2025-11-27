package edu.sena.petcare.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@ToString(exclude = { "veterinaryClinicServices", "facturas", "documentTypeVeterinary" })
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "veterinary_clinic", uniqueConstraints = @UniqueConstraint(name = "uk_veterinary", columnNames = {
        "id_document_type", "document_number" }))
public class VeterinaryClinic {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 255)
    private String address;

    @Column(nullable = false, length = 12)
    private String phone;

    @Column(nullable = false, length = 255)
    private String email;

    @Column(name = "document_number", nullable = false, length = 100)
    @EqualsAndHashCode.Include
    private String documentNumber;

    @Column(nullable = false)
    @ColumnDefault("5.0")
    private Double puntuacion = 5.0;

    @Column(length = 255)
    private String ubicacion;

    @Column(name = "horario_principal", columnDefinition = "TEXT")
    private String horarioPrincipal;

    // relacion OneToMany con VeterinaryClinic
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_document_type")
    private DocumentType documentTypeVeterinary;

    // relacion OneToMany con VeterinaryClinicService
    @OneToMany(mappedBy = "veterinaryClinic", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<VeterinaryClinicService> veterinaryClinicServices;

    // relacion OneToMany con Bill
    @OneToMany(mappedBy = "veterinaryClinic", fetch = FetchType.LAZY)
    private List<Bill> facturas;
}
