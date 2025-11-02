package edu.sena.petcare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edu.sena.petcare.models.VaccinationHistory;

@Repository
public interface VaccinationHistoryRepository extends JpaRepository<VaccinationHistory, Long> {
}
