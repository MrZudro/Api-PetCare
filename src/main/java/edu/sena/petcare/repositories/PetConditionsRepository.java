package edu.sena.petcare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import edu.sena.petcare.models.PetConditions;

@Repository
public interface PetConditionsRepository extends JpaRepository<PetConditions, Long> {

    @Query("SELECT pc FROM PetConditions pc WHERE pc.pet.id = :petId AND pc.fechaFin >= CURRENT_DATE")
    List<PetConditions> findActiveConditionsByPetId(@Param("petId") Long petId);
}
