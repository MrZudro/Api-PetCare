package edu.sena.petcare.services.employee;

import java.util.List;

import edu.sena.petcare.dto.employee.EmployeeNewUpdateDTO;
import edu.sena.petcare.dto.employee.EmployeeReadDTO;

public interface EmployeeService {
    EmployeeReadDTO create(EmployeeNewUpdateDTO dto);

    List<EmployeeReadDTO> findAll();

    EmployeeReadDTO findById(Long id);

    EmployeeReadDTO update(Long id, EmployeeNewUpdateDTO dto);

    void delete(Long id);

    List<EmployeeReadDTO> findByVeterinaryClinicId(Long veterinaryClinicId);
}
