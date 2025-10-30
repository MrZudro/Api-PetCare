package edu.sena.petcare.models;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import java.time.*;
import java.util.List;

import org.hibernate.annotations.*;

import edu.sena.petcare.models.enums.Rol;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"tokens", "documentType", "barrioCliente"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Inheritance(strategy = InheritanceType.JOINED) //Herencia para las otras entidades
@Table(name = "user")
@SQLDelete(sql = "UPDATE user SET is_deleted = true WHERE id = ?")
@FilterDef(
    name = "activeUsersFilter", // Nombre único para el filtro
    parameters = @ParamDef(name = "isDeleted", type = Boolean.class)
)
@Filter(
    name = "activeUsersFilter", 
    condition = "is_deleted = :isDeleted" // Usamos el campo y el parámetro del filtro
)
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(length = 100, nullable = false)
    private String names;

    @Column(name = "last_names", length = 100, nullable = false)
    private String lastNames;

    @Column(name = "document_number", length = 20, nullable = false)
    private String documentNumber;

    @Column(name = "email", length = 200, nullable = false)
    @EqualsAndHashCode.Include
    private String email;

    @Column(name = "password_hash", length = 60, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Rol role;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "created_date", nullable = false)
    private Instant createdDate = Instant.now();

    @Column(name = "reset_key", length = 20)
    private String resetKey;

    @Column(name = "reset_date")
    private LocalDateTime resetDate;

    @Column(length = 200, nullable = false)
    private String address;

    @Column(length = 20, nullable = false)
    private String phone;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    // ---------------- RELACIONES -----------------

    //relacion OneToMany con Token
    @OneToMany(mappedBy = "user")
    List<Token> tokens;

    //relacion ManyToOne con DocumentType
    @ManyToOne
    @JoinColumn(name = "id_document_type", nullable = false)
    private DocumentType documentType;

    //relacion ManyToOne con Neighborhood
    @ManyToOne
    @JoinColumn(name = "id_neighborhood")
    private Neighborhood barrioCliente;

}
