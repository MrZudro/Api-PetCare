package edu.sena.petcare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.sena.petcare.models.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    java.util.List<Bill> findByCustomerIdOrderByCreateDateDesc(Long customerId);
}
