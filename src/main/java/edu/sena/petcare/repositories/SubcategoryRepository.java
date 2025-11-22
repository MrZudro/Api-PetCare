package edu.sena.petcare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.sena.petcare.models.Subcategory;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {
}
