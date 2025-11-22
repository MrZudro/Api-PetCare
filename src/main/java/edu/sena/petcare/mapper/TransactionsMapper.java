package edu.sena.petcare.mapper;

import org.springframework.stereotype.Component;

import edu.sena.petcare.dto.transactions.TransactionsNewUpdateDTO;
import edu.sena.petcare.dto.transactions.TransactionsReadDTO;
import edu.sena.petcare.models.Transactions;

@Component
public class TransactionsMapper {

    public TransactionsReadDTO toDto(Transactions transaction) {
        if (transaction == null) {
            return null;
        }
        TransactionsReadDTO dto = new TransactionsReadDTO();
        dto.setId(transaction.getId());
        dto.setTotalValue(transaction.getTotalValue());
        dto.setAnswerJsonGateway(transaction.getAnswerJsonGateway());
        dto.setTransactionDate(transaction.getTransactionDate());
        return dto;
    }

    public Transactions toEntity(TransactionsNewUpdateDTO dto) {
        if (dto == null) {
            return null;
        }
        Transactions transaction = new Transactions();
        transaction.setTotalValue(dto.getTotalValue());
        transaction.setAnswerJsonGateway(dto.getAnswerJsonGateway());
        return transaction;
    }

    public void updateEntity(TransactionsNewUpdateDTO dto, Transactions transaction) {
        if (dto == null || transaction == null) {
            return;
        }
        transaction.setTotalValue(dto.getTotalValue());
        transaction.setAnswerJsonGateway(dto.getAnswerJsonGateway());
    }
}
