package edu.sena.petcare.mapper;

import org.springframework.stereotype.Component;
import edu.sena.petcare.dto.employee.EmployeeNewUpdateDTO;
import edu.sena.petcare.dto.employee.EmployeeReadDTO;
import edu.sena.petcare.models.Employee;
import edu.sena.petcare.models.enums.EmployeeCargo;
import edu.sena.petcare.utility.ListaMappeo;
import java.util.List;

@Component
public class EmployeeMapper {

        public EmployeeReadDTO toDto(Employee entity) {
                if (entity == null) {
                        return null;
                }
                EmployeeReadDTO dto = new EmployeeReadDTO();
                dto.setId(entity.getId());
                dto.setNames(entity.getNames());
                dto.setLastNames(entity.getLastNames());
                dto.setDocumentNumber(entity.getDocumentNumber());
                dto.setEmail(entity.getEmail());
                dto.setBirthDate(entity.getBirthDate());

                dto.setPhone(entity.getPhone());
                dto.setDocumentTypeId(entity.getDocumentType() != null ? entity.getDocumentType().getId() : null);
                dto.setNeighborhoodId(entity.getBarrioCliente() != null ? entity.getBarrioCliente().getId() : null);
                dto.setEmployeeCode(entity.getEmployeeCode());
                dto.setSalary(entity.getSalary());
                dto.setCargo(entity.getCargo() != null ? entity.getCargo().name() : null);
                return dto;
        }

        public Employee toEntity(EmployeeNewUpdateDTO dto) {
                if (dto == null) {
                        return null;
                }
                Employee entity = new Employee();
                entity.setNames(dto.getNames());
                entity.setLastNames(dto.getLastNames());
                entity.setDocumentNumber(dto.getDocumentNumber());
                entity.setEmail(dto.getEmail());
                entity.setPassword(dto.getPassword());
                entity.setBirthDate(dto.getBirthDate());

                entity.setPhone(dto.getPhone());
                entity.setEmployeeCode(dto.getEmployeeCode());
                entity.setSalary(dto.getSalary());
                if (dto.getCargo() != null) {
                        entity.setCargo(EmployeeCargo.valueOf(dto.getCargo()));
                }
                // Relationships handled in Service
                return entity;
        }

        public void updateEntity(EmployeeNewUpdateDTO dto, Employee entity) {
                if (dto == null || entity == null) {
                        return;
                }
                if (dto.getNames() != null)
                        entity.setNames(dto.getNames());
                if (dto.getLastNames() != null)
                        entity.setLastNames(dto.getLastNames());
                if (dto.getDocumentNumber() != null)
                        entity.setDocumentNumber(dto.getDocumentNumber());
                if (dto.getEmail() != null)
                        entity.setEmail(dto.getEmail());
                if (dto.getPassword() != null)
                        entity.setPassword(dto.getPassword());
                if (dto.getBirthDate() != null)
                        entity.setBirthDate(dto.getBirthDate());

                if (dto.getPhone() != null)
                        entity.setPhone(dto.getPhone());
                if (dto.getEmployeeCode() != null)
                        entity.setEmployeeCode(dto.getEmployeeCode());
                if (dto.getSalary() != null)
                        entity.setSalary(dto.getSalary());
                if (dto.getCargo() != null)
                        entity.setCargo(EmployeeCargo.valueOf(dto.getCargo()));
                // Relationships handled in Service
        }

        public List<EmployeeReadDTO> toDtoList(List<Employee> entities) {
                return ListaMappeo.toDtoList(entities, this::toDto);
        }
}
