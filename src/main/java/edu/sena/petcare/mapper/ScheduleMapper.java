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
        dto.setDay(entity.getDay());
        dto.setStart_time(entity.getStart_time());
        dto.setEnd_time(entity.getEnd_time());
        return dto;
    }

    public Schedule toEntity(ScheduleNewUpdateDTO dto) {
        if (dto == null) {
            return null;
        }
        Schedule entity = new Schedule();
        entity.setDay(dto.getDay());
        entity.setStart_time(dto.getStart_time());
        entity.setEnd_time(dto.getEnd_time());
        // Employee relationship handled in Service
        return entity;
    }

    public void updateEntity(ScheduleNewUpdateDTO dto, Schedule entity) {
        if (dto == null || entity == null) {
            return;
        }
        if (dto.getDay() != null)
            entity.setDay(dto.getDay());
        if (dto.getStart_time() != null)
            entity.setStart_time(dto.getStart_time());
        if (dto.getEnd_time() != null)
            entity.setEnd_time(dto.getEnd_time());
        // Employee relationship handled in Service
    }

    public List<ScheduleReadDTO> toDtoList(List<Schedule> entities) {
        return ListaMappeo.toDtoList(entities, this::toDto);
    }
}