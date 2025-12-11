package edu.sena.petcare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.sena.petcare.models.Neighborhood;

@Repository
public interface NeighborhoodRepository extends JpaRepository<Neighborhood, Long> {

    java.util.List<Neighborhood> findByLocalityId(Long localityId);
}
