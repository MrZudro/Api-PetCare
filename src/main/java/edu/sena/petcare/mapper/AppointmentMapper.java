package edu.sena.petcare.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import edu.sena.petcare.dto.appointment.AppointmentNewUpdateDTO;
import edu.sena.petcare.dto.appointment.AppointmentReadDTO;
import edu.sena.petcare.models.Appointment;
import edu.sena.petcare.models.enums.AppointmentStatus;

import java.util.List;

@Mapper(componentModel = "spring") // Usamos "spring" para inyección automática
public interface AppointmentMapper {

    // 0. Obtención del mapper (para uso manual si es necesario)
    AppointmentMapper mapper = Mappers.getMapper(AppointmentMapper.class);

    // 1. Mapeo de Entidad a DTO de Lectura
    /**
     * @Documented Convierte la Entidad Appointment a DTO de Lectura.
     * Mapea los nombres de las entidades relacionadas para la vista del usuario.
     */
    @Mapping(target = "customerName", source = "customer.name")
    @Mapping(target = "veterinaryClinicName", source = "veterinaryClinic.name")
    @Mapping(target = "employeeName", source = "employee.name")
    AppointmentReadDTO toReadDto(Appointment entity);
    List<AppointmentReadDTO> toReadDtoList(List<Appointment> entities);

    // 2. Mapeo de DTO de Solicitud a Entidad (para CREAR)
    /**
     * @Documented Convierte DTO de Creación a Entidad.
     * Ignora el ID y las entidades relacionadas, se asignarán en el Servicio.
     * Fija el estado inicial como PENDING.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "veterinaryClinic", ignore = true)
    @Mapping(target = "employee", ignore = true)
    @Mapping(target = "status", constant = "PENDING") 
    Appointment toEntity(AppointmentNewUpdateDTO dto);
    
    // 3. Método para actualizar una Entidad existente
    /**
     * @Documented Actualiza la Entidad existente con los datos del DTO.
     * Ignora el ID y las entidades relacionadas para evitar sobrescribir las referencias.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "veterinaryClinic", ignore = true)
    @Mapping(target = "employee", ignore = true)
    void updateEntity(AppointmentNewUpdateDTO dto, @MappingTarget Appointment entity);
}