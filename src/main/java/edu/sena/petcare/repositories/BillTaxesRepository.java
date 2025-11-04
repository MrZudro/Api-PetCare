package edu.sena.petcare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.sena.petcare.models.BillTaxes;

public interface BillTaxesRepository extends JpaRepository<BillTaxes, Long> {
}