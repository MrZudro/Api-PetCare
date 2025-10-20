package edu.sena.petcare.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"bill", "methodTransaction"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "transactions")
public class Transactions {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "id_transaction_gateway",  length = 255, unique = true, nullable = false)
    @EqualsAndHashCode.Include
    private String idTransactionGateway;

    @Column(name = "state_gateway", nullable = false)
    private Integer stateGateway;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false, length = 3)
    private String money;

    @Column(nullable = false)
    private LocalDateTime dateTransaction;

    @Column(length = 500)
    private String answerJsonGateway;

    //relacion OneToMany con Bill
    @ManyToOne
    @JoinColumn(name = "id_bill")
    private Bill bill;

    //relacion OneToMany con MethodPaymentCustomer
    @ManyToOne
    @JoinColumn(name = "id_payment_saved")
    private MethodPaymentCustomer methodTransaction;
}
