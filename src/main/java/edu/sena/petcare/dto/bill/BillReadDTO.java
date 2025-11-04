package edu.sena.petcare.dto.bill;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillReadDTO {

    private Long id;
    private String prefix;
    private Long consecutive;
    private String resolution;
    private String cufe;
    private LocalDateTime createDate;
    private LocalDateTime dateValidationDian;
    private BigDecimal totalBill;
    private String dianState;
    private String veterinaryClinicName; // Nombre de la clínica
    private String methodPaymentName;    // Nombre del método de pago
    private List<BillDetailReadDTO> details; // Detalles de los productos comprados
}