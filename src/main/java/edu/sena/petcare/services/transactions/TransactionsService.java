package edu.sena.petcare.services.transactions;

import java.util.List;

import edu.sena.petcare.dto.transactions.TransactionsReadDTO;

public interface TransactionsService {
    List<TransactionsReadDTO> getAll();

    TransactionsReadDTO getById(Long id);
}
