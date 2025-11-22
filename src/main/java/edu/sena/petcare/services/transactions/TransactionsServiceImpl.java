package edu.sena.petcare.services.transactions;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.sena.petcare.dto.transactions.TransactionsReadDTO;
import edu.sena.petcare.mapper.TransactionsMapper;
import edu.sena.petcare.repositories.TransactionsRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionsServiceImpl implements TransactionsService {

    private final TransactionsRepository transactionsRepository;
    private final TransactionsMapper transactionsMapper;

    @Override
    public List<TransactionsReadDTO> getAll() {
        return transactionsRepository.findAll()
                .stream()
                .map(transactionsMapper::toDto)
                .toList();
    }

    @Override
    public TransactionsReadDTO getById(Long id) {
        return transactionsRepository.findById(id)
                .map(transactionsMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Transaction not found with id: " + id));
    }
}
