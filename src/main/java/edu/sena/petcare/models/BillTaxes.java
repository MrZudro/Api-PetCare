package edu.sena.petcare.models;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = { "bill", "billDetail" })
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "bill_taxes")
public class BillTaxes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "tax_code", length = 10, nullable = false)
    private String taxCode;

    @Column(name = "name_tax", length = 50, nullable = false)
    private String nameTax;

    @Column(name = "tax_base", precision = 19, scale = 2, nullable = false)
    private BigDecimal taxBase;

    @Column(name = "percentage", precision = 5, scale = 2, nullable = false)
    private BigDecimal percentage;

    @Column(name = "tax_value", precision = 19, scale = 2, nullable = false)
    private BigDecimal taxValue;

    // relacion OneToMany con Bill
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bill")
    private Bill bill;

    // relacion OneToMany con BillDetail
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_detail_bill")
    private BillDetail billDetail;
}
