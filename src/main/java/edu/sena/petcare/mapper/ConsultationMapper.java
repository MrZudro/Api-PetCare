package edu.sena.petcare.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import edu.sena.petcare.dto.consultation.ConsultationNewDTO;
import edu.sena.petcare.dto.consultation.ConsultationReadDTO;
import edu.sena.petcare.models.Consultation;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ConsultationMapper {

    // 0. Instancia del Mapper
    ConsultationMapper mapper = Mappers.getMapper(ConsultationMapper.class);

    // 1. Mapeo de Entidad a DTO de Lectura
    /**
     * @Documented Convierte la Entidad Consultation a DTO de Lectura,
     * obteniendo los nombres de las entidades relacionadas para la visualización.
     */
    @Mapping(target = "employeeName", source = "employee.name")
    @Mapping(target = "veterinaryClinicName", source = "veterinaryClinic.name")
    @Mapping(target = "petName", source = "pet.name")
    @Mapping(target = "petId", source = "pet.id")
    ConsultationReadDTO toReadDto(Consultation entity);
    List<ConsultationReadDTO> toReadDtoList(List<Consultation> entities);

    // 2. Mapeo de DTO de Solicitud a Entidad (solo para CREAR)
    /**
     * @Documented Convierte DTO de Creación a Entidad.
     * Ignora el ID y las entidades relacionadas, ya que se asignarán en el Servicio.
     * El estado inicial será COMPLETED (asumiendo que al registrar, la consulta ya ocurrió).
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employee", ignore = true)
    @Mapping(target = "veterinaryClinic", ignore = true)
    @Mapping(target = "pet", ignore = true)
    @Mapping(target = "status", constant = "COMPLETED") // Estado predeterminado
    Consultation toEntity(ConsultationNewDTO dto);
    
    // NOTA: No se implementa updateEntity debido a la restricción.
}