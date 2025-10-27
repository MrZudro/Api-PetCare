package edu.sena.petcare.repositories;

import edu.sena.petcare.models.Mascotas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MascotasRepository extends JpaRepository<Mascotas, Long> {
    
}
