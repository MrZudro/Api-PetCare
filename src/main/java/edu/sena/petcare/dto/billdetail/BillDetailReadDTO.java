package edu.sena.petcare.dto.billdetail;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillDetailReadDTO {
    private Long id;
    private Long productId;
    private String productName;
    private BigDecimal amount;
    private BigDecimal unitPrice;
    private BigDecimal subtotalLine;
    private String imageUrl;
}
