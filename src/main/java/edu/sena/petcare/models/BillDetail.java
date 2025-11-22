package edu.sena.petcare.models;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = { "bill", "product", "taxes" })
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "bill_detail")
public class BillDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal amount;

    @Column(name = "unit_price", precision = 10, scale = 2, nullable = false)
    private BigDecimal unitPrice;

    @Column(name = "subtotal_line", precision = 10, scale = 2, nullable = false)
    private BigDecimal subtotalLine;

    // relacion OneToMany con Bill
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bill")
    private Bill bill;

    // relacion OneToMany con Products
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_product")
    private Product product;

    // relacion OneToMany con BillTaxes
    @OneToMany(mappedBy = "billDetail", fetch = FetchType.LAZY)
    private List<BillTaxes> taxes;

}
