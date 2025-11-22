package edu.sena.petcare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.sena.petcare.models.PetConditions;

@Repository
public interface PetConditionsRepository extends JpaRepository<PetConditions, Long> {
}
