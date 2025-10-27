package edu.sena.petcare.mapper;

import java.util.List;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import edu.sena.petcare.dto.subcategory.SubcategoryNewUpdateDTO;
import edu.sena.petcare.dto.subcategory.SubcategoryReadDTO;
import edu.sena.petcare.models.Subcategory;

@Mapper
public interface SubcategoryMapper {

    // Obtención del mapper en tiempo de compilación (Standalone)
    SubcategoryMapper INSTANCE = Mappers.getMapper(SubcategoryMapper.class);

    // Mapeo de Entidad a DTO de Lectura
    @Mapping(target = "categoryId", source = "categoria.id")
    @Mapping(target = "categoryName", source = "categoria.name")
    SubcategoryReadDTO toDto(Subcategory entity);
    
    // Mapeo de lista de entidades a lista de DTOs
    List<SubcategoryReadDTO> toDtoList(List<Subcategory> entities);

    // Mapeo de DTO de Creación/Actualización a Entidad
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categoria", ignore = true)  // Se maneja en el servicio con categoryId
    @Mapping(target = "productos", ignore = true)  // Se maneja en el servicio
    Subcategory toEntity(SubcategoryNewUpdateDTO dto);
    
    // Actualización de Entidad existente desde DTO
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categoria", ignore = true)  // Se maneja en el servicio con categoryId
    @Mapping(target = "productos", ignore = true)  // Se maneja en el servicio
    void updateEntityFromDto(SubcategoryNewUpdateDTO dto, @MappingTarget Subcategory entity);
}
