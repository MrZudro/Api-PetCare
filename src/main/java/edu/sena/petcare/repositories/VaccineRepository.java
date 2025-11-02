package edu.sena.petcare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edu.sena.petcare.models.Vaccine;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Long> {
}
