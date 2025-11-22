package edu.sena.petcare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.sena.petcare.models.MethodPaymentCustomer;

@Repository
public interface MethodPaymentCustomerRepository extends JpaRepository<MethodPaymentCustomer, Long> {
}
