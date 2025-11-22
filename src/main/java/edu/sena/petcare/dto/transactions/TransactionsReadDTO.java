package edu.sena.petcare.dto.transactions;

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
public class TransactionsReadDTO {
    private Long id;
    private BigDecimal totalValue;
    private String answerJsonGateway;
    private LocalDateTime transactionDate;
}
