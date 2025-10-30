package edu.sena.petcare.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import edu.sena.petcare.models.Conditions;

public interface ConditionsRepository extends JpaRepository<Conditions, Long> {

}
