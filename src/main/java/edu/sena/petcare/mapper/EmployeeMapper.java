package edu.sena.petcare.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import edu.sena.petcare.dto.employee.EmployeeNewUpdateDTO;
import edu.sena.petcare.dto.employee.EmployeeReadDTO;
import edu.sena.petcare.models.Employee;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mappings({
            @Mapping(source = "documentType.id", target = "documentTypeId"),
            @Mapping(source = "barrioCliente.id", target = "neighborhoodId")
    })
    EmployeeReadDTO toDto(Employee entity);

    List<EmployeeReadDTO> toDtoList(List<Employee> entities);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "role", ignore = true),
            @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "resetKey", ignore = true),
            @Mapping(target = "resetDate", ignore = true),
            @Mapping(target = "tokens", ignore = true),
            @Mapping(target = "documentType", ignore = true),
            @Mapping(target = "barrioCliente", ignore = true),
            @Mapping(target = "facturas", ignore = true),
            @Mapping(target = "deleted", ignore = true)
    })
    Employee toEntity(EmployeeNewUpdateDTO dto);
}


