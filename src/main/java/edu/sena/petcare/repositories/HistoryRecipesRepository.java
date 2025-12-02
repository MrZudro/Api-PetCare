package edu.sena.petcare.repositories;

import edu.sena.petcare.models.HistoryRecipes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HistoryRecipesRepository extends JpaRepository<HistoryRecipes, Long> {

    // Obtener recetas activas de una mascota (válidas después de hoy)
    @Query("SELECT hr FROM HistoryRecipes hr WHERE hr.pet.id = :petId AND hr.validUntil >= :currentDate ORDER BY hr.validUntil ASC")
    List<HistoryRecipes> findActivePrescriptionsByPetId(@Param("petId") Long petId,
            @Param("currentDate") LocalDate currentDate);

    // Obtener todas las recetas de una mascota
    List<HistoryRecipes> findByPetId(Long petId);
}
