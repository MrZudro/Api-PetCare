package edu.sena.petcare.repositories;

import edu.sena.petcare.models.VaccinationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VaccinationHistoryRepository extends JpaRepository<VaccinationHistory, Long> {
    // Obtener todas las vacunaciones de una mascota
    List<VaccinationHistory> findByPetId(Long petId);
}
