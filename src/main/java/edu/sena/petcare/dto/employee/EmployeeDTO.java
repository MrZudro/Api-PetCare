package edu.sena.petcare.dto.employee;

import edu.sena.petcare.models.enums.EmployeeCargo;
import lombok.Data;

@Data
public class EmployeeDTO {
    private Long id;
    private String names;
    private String lastNames;
    private EmployeeCargo cargo;
    private Long veterinaryClinicId;
}
