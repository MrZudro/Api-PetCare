package edu.sena.petcare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.sena.petcare.models.BillTaxes;

@Repository
public interface BillTaxesRepository extends JpaRepository<BillTaxes, Long> {
}
