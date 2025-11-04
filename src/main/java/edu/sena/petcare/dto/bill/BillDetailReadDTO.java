package edu.sena.petcare.dto.bill;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDetailReadDTO {

    private Long id;
    private String productName;
    private BigDecimal amount;
    private BigDecimal unitPrice;
    private BigDecimal subtotalLine;
    // Opcional: Podrías incluir la información de impuestos si fuera necesario.
}