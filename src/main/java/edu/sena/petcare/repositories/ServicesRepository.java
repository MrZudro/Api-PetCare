package edu.sena.petcare.repositories;

import edu.sena.petcare.models.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicesRepository extends JpaRepository<Services, Long> {

    @Query("SELECT s FROM Services s JOIN s.veterinaryClinicServices vcs WHERE vcs.veterinaryClinic.id = :clinicId")
    List<Services> findServicesByClinicId(@Param("clinicId") Long clinicId);
}
