package edu.sena.petcare.dto.bill;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillNewUpdateDTO {
    private String resolution;
    private String prefix;
    private Long consecutive;
    private BigDecimal subtotal;
    private BigDecimal taxes;
    private BigDecimal totalBill;
    private String dianState;
}
