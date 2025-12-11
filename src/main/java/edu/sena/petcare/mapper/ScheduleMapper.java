package edu.sena.petcare.mapper;

import org.springframework.stereotype.Component;
import edu.sena.petcare.dto.schedule.ScheduleNewUpdateDTO;
import edu.sena.petcare.dto.schedule.ScheduleReadDTO;
import edu.sena.petcare.models.Schedule;
import edu.sena.petcare.utility.ListaMappeo;
import java.util.List;

@Component
public class ScheduleMapper {

    public ScheduleReadDTO toDto(Schedule entity) {
        if (entity == null) {
            return null;
        }
        ScheduleReadDTO dto = new ScheduleReadDTO();
        dto.setId(entity.getId());
        dto.setEmployeeId(entity.getEmployee() != null ? entity.getEmployee().getId() : null);

        // Datos del empleado
        if (entity.getEmployee() != null) {
            dto.setEmployeeName(entity.getEmployee().getNames() + " " + entity.getEmployee().getLastNames());
            dto.setEmployeeCargo(
                    entity.getEmployee().getCargo() != null ? entity.getEmployee().getCargo().name() : null);
        }

        dto.setDay(entity.getDay());
        dto.setScheduleDate(entity.getScheduleDate());
        dto.setStart_time(entity.getStart_time());
        dto.setEnd_time(entity.getEnd_time());
        dto.setIsOvertime(entity.getIsOvertime());
        dto.setPeriodStartDate(entity.getPeriodStartDate());
        dto.setPeriodEndDate(entity.getPeriodEndDate());

        return dto;
    }

    public Schedule toEntity(ScheduleNewUpdateDTO dto) {
        if (dto == null) {
            return null;
        }
        Schedule entity = new Schedule();
        entity.setDay(dto.getDay());
        entity.setScheduleDate(dto.getScheduleDate());
        entity.setStart_time(dto.getStart_time());
        entity.setEnd_time(dto.getEnd_time());
        entity.setIsOvertime(dto.getIsOvertime() != null ? dto.getIsOvertime() : false);
        entity.setPeriodStartDate(dto.getPeriodStartDate());
        entity.setPeriodEndDate(dto.getPeriodEndDate());
        // Employee relationship handled in Service
        return entity;
    }

    public void updateEntity(ScheduleNewUpdateDTO dto, Schedule entity) {
        if (dto == null || entity == null) {
            return;
        }
        if (dto.getDay() != null)
            entity.setDay(dto.getDay());
        if (dto.getScheduleDate() != null)
            entity.setScheduleDate(dto.getScheduleDate());
        if (dto.getStart_time() != null)
            entity.setStart_time(dto.getStart_time());
        if (dto.getEnd_time() != null)
            entity.setEnd_time(dto.getEnd_time());
        if (dto.getIsOvertime() != null)
            entity.setIsOvertime(dto.getIsOvertime());
        if (dto.getPeriodStartDate() != null)
            entity.setPeriodStartDate(dto.getPeriodStartDate());
        if (dto.getPeriodEndDate() != null)
            entity.setPeriodEndDate(dto.getPeriodEndDate());
        // Employee relationship handled in Service
    }

    public List<ScheduleReadDTO> toDtoList(List<Schedule> entities) {
        return ListaMappeo.toDtoList(entities, this::toDto);
    }
}