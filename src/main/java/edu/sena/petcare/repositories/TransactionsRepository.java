package edu.sena.petcare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.sena.petcare.models.Transactions;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Long> {
}
