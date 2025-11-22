package edu.sena.petcare.repositories;

import edu.sena.petcare.models.VeterinaryClinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeterinaryClinicRepository extends JpaRepository<VeterinaryClinic, Long> {
}
