package edu.sena.petcare.mapper;

import org.springframework.stereotype.Component;
import edu.sena.petcare.dto.appointment.AppointmentNewUpdateDTO;
import edu.sena.petcare.dto.appointment.AppointmentReadDTO;
import edu.sena.petcare.models.Appointment;
import edu.sena.petcare.models.enums.AppointmentStatus;
import edu.sena.petcare.utility.ListaMappeo;
import java.util.List;

@Component
public class AppointmentMapper {

    public AppointmentReadDTO toDto(Appointment entity) {
        if (entity == null) {
            return null;
        }
        AppointmentReadDTO dto = new AppointmentReadDTO();
        dto.setId(entity.getId());
        dto.setAppointmentDateTime(entity.getAppointmentDateTime());
        dto.setStatus(entity.getStatus());
        dto.setReason(entity.getReason());

        if (entity.getCustomer() != null) {
            dto.setCustomerId(entity.getCustomer().getId());
            dto.setCustomerName(entity.getCustomer().getNames());
        }

        if (entity.getVeterinaryClinic() != null) {
            dto.setVeterinaryClinicId(entity.getVeterinaryClinic().getId());
            dto.setVeterinaryClinicName(entity.getVeterinaryClinic().getName());
        }

        if (entity.getEmployee() != null) {
            dto.setEmployeeId(entity.getEmployee().getId());
            dto.setEmployeeName(entity.getEmployee().getNames());
        }

        if (entity.getService() != null) {
            dto.setServiceId(entity.getService().getId());
            dto.setServiceName(entity.getService().getName());
        }

        return dto;
    }

    public Appointment toEntity(AppointmentNewUpdateDTO dto) {
        if (dto == null) {
            return null;
        }
        Appointment entity = new Appointment();
        entity.setAppointmentDateTime(dto.getAppointmentDateTime());
        entity.setReason(dto.getReason());
        entity.setStatus(AppointmentStatus.PENDING);
        // Relationships handled in Service
        return entity;
    }

    public void updateEntity(AppointmentNewUpdateDTO dto, Appointment entity) {
        if (dto == null || entity == null) {
            return;
        }
        if (dto.getAppointmentDateTime() != null) {
            entity.setAppointmentDateTime(dto.getAppointmentDateTime());
        }
        if (dto.getReason() != null) {
            entity.setReason(dto.getReason());
        }
        if (dto.getStatus() != null) {
            entity.setStatus(dto.getStatus());
        }
        // Relationships handled in Service
    }

    public List<AppointmentReadDTO> toDtoList(List<Appointment> entities) {
        return ListaMappeo.toDtoList(entities, this::toDto);
    }
}
