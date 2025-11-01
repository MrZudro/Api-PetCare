package edu.sena.petcare.repositories;


import edu.sena.petcare.models.ExamDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamDetailRepository extends JpaRepository<ExamDetail, Long> {
}