package edu.sena.petcare.dto.transactions;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionsNewUpdateDTO {
    private BigDecimal totalValue;
    private String answerJsonGateway;
}
