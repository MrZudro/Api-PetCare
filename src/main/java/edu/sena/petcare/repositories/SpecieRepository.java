package edu.sena.petcare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.sena.petcare.models.Specie;

@Repository
public interface SpecieRepository extends JpaRepository<Specie, Long> {
}
