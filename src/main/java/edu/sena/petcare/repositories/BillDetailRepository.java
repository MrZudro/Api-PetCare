package edu.sena.petcare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.sena.petcare.models.BillDetail;

@Repository
public interface BillDetailRepository extends JpaRepository<BillDetail, Long> {
}
