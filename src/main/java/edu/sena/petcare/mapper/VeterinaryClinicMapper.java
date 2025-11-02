package edu.sena.petcare.mapper;

import java.util.List;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import edu.sena.petcare.dto.veterinaryClinic.VeterinaryClinicNewUpdateDTO;
import edu.sena.petcare.dto.veterinaryClinic.VeterinaryClinicReadDTO;
import edu.sena.petcare.models.VeterinaryClinic;

@Mapper
public interface VeterinaryClinicMapper {

    // 0. Obtención del mapper en tiempo de compilación, tipo Standalone
    VeterinaryClinicMapper mapper = Mappers.getMapper(VeterinaryClinicMapper.class);

    // 1. Mapeo de Entidad a DTO de Lectura (VeterinaryClinic -> VeterinaryClinicReadDTO)
    // Se extrae el ID del DocumentType (DocumentTypeVeterinary)
    @Mapping(target = "documentTypeId", source = "documentTypeVeterinary.id")
    // Se mapean las listas de IDs de las relaciones OneToMany
    @Mapping(target = "serviceIds", expression = "java(entity.getVeterinaryClinicServices().stream().map(s -> s.getId()).toList())")
    @Mapping(target = "billIds", expression = "java(entity.getFacturas().stream().map(f -> f.getId()).toList())")
    VeterinaryClinicReadDTO toDto(VeterinaryClinic entity);

    List<VeterinaryClinicReadDTO> toDtoList(List<VeterinaryClinic> entities);

    // 2. Mapeo de DTO de Solicitud a Entidad (VeterinaryClinicNewUpdateDTO -> VeterinaryClinic)
    // El ID se ignora para creación/actualización
    @Mapping(target = "id", ignore = true)
    // Las relaciones complejas se ignoran, ya que se asume que el servicio se encarga de buscar y asignar la entidad DocumentType
    @Mapping(target = "documentTypeVeterinary", ignore = true) 
    // Las colecciones de relaciones se ignoran, se asume que se inicializan vacías o se manejan en el servicio
    @Mapping(target = "veterinaryClinicServices", ignore = true) 
    @Mapping(target = "facturas", ignore = true) 
    VeterinaryClinic toEntity(VeterinaryClinicNewUpdateDTO dto);

    // 3. Método para actualizar una Entidad existente (actualiza los campos de dto en entity)
    @Mapping(target = "id", ignore = true) // No se debe actualizar el ID
    // Se ignoran las relaciones que se manejan en el servicio o no deben ser modificadas por el DTO
    @Mapping(target = "documentTypeVeterinary", ignore = true) 
    @Mapping(target = "veterinaryClinicServices", ignore = true) 
    @Mapping(target = "facturas", ignore = true) 
    void updateEntity(VeterinaryClinicNewUpdateDTO dto, @MappingTarget VeterinaryClinic entity);

    // 4. NUEVO MÉTODO: Mapeo de Entidad a DTO de Actualización (para ser retornado en el servicio)
    // Mapea la Entidad de vuelta al DTO, extrayendo el ID del DocumentType
    @Mapping(target = "documentTypeId", source = "documentTypeVeterinary.id")
    VeterinaryClinicNewUpdateDTO toUpdateDto(VeterinaryClinic entity);

}