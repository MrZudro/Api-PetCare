package edu.sena.petcare.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.*;
import java.util.List;

import edu.sena.petcare.models.enums.Rol;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"tokens", "documentType", "barrioCliente", "pets", "wishlists", "paymentMethods"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name ="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(length = 100, nullable = false)
    private String names;

    @Column(name = "last_names", length = 100, nullable = false)
    private String lastNames;

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

    //relacion OneToMany con Pet
    @OneToMany(mappedBy = "user")
    private List<Pet> pets;

    //relacion OneToMany con Wishlist
    @OneToMany(mappedBy = "user")
    private List<Wishlist> wishlists;

    //relacion OneToMany con MethodPaymentCustomer
    @OneToMany(mappedBy = "user")
    private List<MethodPaymentCustomer> paymentMethods;
}
