package edu.sena.petcare.dto.bill;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillReadDTO {
    private Long id;
    private String resolution;
    private String prefix;
    private Long consecutive;
    private String cufe;
    private LocalDateTime createDate;
    private LocalDateTime dateValidationDian;
    private BigDecimal subtotal;
    private BigDecimal taxes;
    private BigDecimal totalBill;
    private String dianState;
}
