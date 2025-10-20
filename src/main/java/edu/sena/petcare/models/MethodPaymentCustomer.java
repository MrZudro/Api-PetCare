package edu.sena.petcare.models;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"user", "bill", "transactions"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "method_payment_customer")
public class MethodPaymentCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "token_payment_method", length = 255, unique = true, nullable = false)
    @EqualsAndHashCode.Include
    private String tokenPaymentMethod;

    @Column(name = "token_client_gateway", length = 255, nullable = false)
    private String tokenClientGateway;

    @Column(length = 50, nullable = false)
    private String brand;

    @Column(name = "last_four_digits", length = 50, nullable = false)
    private Integer lastFourDigits;

    @Column(name = "expiration_date",length = 7, nullable = false)
    private String expirationDate;

    @Column(name="is_default", nullable = false)
    private Boolean isDefault;

    @Column(name="creation_date", nullable = false)
    private LocalDateTime creationDate;

    //relacion OneToMany con User
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    //relacion OneToMany con Bill
    @OneToMany(mappedBy = "metodoCliente")
    private List<Bill> bill;

    //relacion OneToMany con Transactions
    @OneToMany(mappedBy = "methodTransaction")
    private List<Transactions> transactions;
}
