package edu.sena.petcare.dto.billtaxes;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillTaxesReadDTO {
    private Long id;
    private String taxCode;
    private String nameTax;
    private BigDecimal taxBase;
    private BigDecimal percentage;
    private BigDecimal taxValue;
}
