package edu.sena.petcare.models;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"billDetails", "billTaxes", "billTransactions", "veterinaryClinic", "metodoCliente"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, length = 255)
    private String resolution;

    @Column(nullable = false, length = 10)
    private String prefix;

    @Column(nullable = false)
    private Long consecutive;

    @Column(unique = true, length = 255)
    @EqualsAndHashCode.Include
    private String cufe;

    @Column(name = "create_date",nullable = false)
    private LocalDateTime createDate;

    @Column(name = "date_validation_dian")
    private LocalDateTime dateValidationDian;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal subtotal;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal taxes;

    @Column(name = "total_bill", nullable = false, precision = 19, scale = 2)
    private BigDecimal totalBill;

    @Column(name = "dian_state",length = 255)
    private String dianState;

    //relacion OneToMany con VeterinaryClinic
    @ManyToOne
    @JoinColumn(name = "id_veterinary")
    private VeterinaryClinic veterinaryClinic;

    //relacion OneToMany con MethodPaymentCustomer
    @ManyToOne
    @JoinColumn(name = "id_method_payment")
    private MethodPaymentCustomer metodoCliente;

    //relacion OneToMany con BillDetail
    @OneToMany(mappedBy = "bill")
    private List<BillDetail> billDetails;

    //relacion OneToOne con BillTaxes
    @OneToMany(mappedBy = "bill")
    private List<BillTaxes> billTaxes;

    //relacion OneToMany con Transactions
    @OneToMany(mappedBy = "bill")
    private List<Transactions> billTransactions;

    //relacion con Employee
    @ManyToOne
    @JoinColumn(name = "id_employee")
    private Employee empleado;
}
