package edu.sena.petcare.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import edu.sena.petcare.dto.schedule.ScheduleReadDTO;
import edu.sena.petcare.dto.schedule.ScheduleNewUpdateDTO;
import edu.sena.petcare.models.Schedule;
import edu.sena.petcare.models.Employee;

@Mapper
public interface ScheduleMapper {

    ScheduleMapper mapper = Mappers.getMapper(ScheduleMapper.class);

    @Mapping(target = "employeeId", source = "employee.id")
    ScheduleReadDTO toDto(Schedule entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employee", expression = "java(toEmployee(dto.getEmployeeId()))")
    Schedule toEntity(ScheduleNewUpdateDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employee", expression = "java(toEmployee(dto.getEmployeeId()))")
    void updateEntity(ScheduleNewUpdateDTO dto, @MappingTarget Schedule entity);

    default Employee toEmployee(Long employeeId) {
        if (employeeId == null) return null;
        Employee e = new Employee();
        e.setId(employeeId);
        return e;
    }
}