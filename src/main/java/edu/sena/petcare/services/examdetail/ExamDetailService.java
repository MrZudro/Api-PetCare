package edu.sena.petcare.services.examdetail;


import edu.sena.petcare.dto.examdetail.ExamDetailNewUpdateDTO;
import edu.sena.petcare.dto.examdetail.ExamDetailReadDTO;
import java.util.List;

public interface ExamDetailService {

    List<ExamDetailReadDTO> findAll();
    ExamDetailReadDTO findById(Long id);
    ExamDetailReadDTO create(ExamDetailNewUpdateDTO dto);
    ExamDetailReadDTO update(Long id, ExamDetailNewUpdateDTO dto);
    void delete(Long id); // Se permite el borrado ya que no hay soft-delete
}