package edu.sena.petcare.mapper;

import java.util.List;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import edu.sena.petcare.dto.category.CategoryNewUpdateDTO;
import edu.sena.petcare.dto.category.CategoryReadDTO;
import edu.sena.petcare.models.Category;

@Mapper
public interface CategoryMapper {

    // Obtención del mapper en tiempo de compilación (Standalone)
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    // Mapeo de Entidad a DTO de Lectura
    @Mapping(target = "subcategoriaIds", expression = "java(entity.getSubcategorias() == null ? java.util.Collections.emptyList() : entity.getSubcategorias().stream().map(sub -> sub.getId()).collect(java.util.stream.Collectors.toList()))")
    CategoryReadDTO toDto(Category entity);
    
    // Mapeo de lista de entidades a lista de DTOs
    List<CategoryReadDTO> toDtoList(List<Category> entities);

    // Mapeo de DTO de Creación/Actualización a Entidad
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "subcategorias", ignore = true)  // Se maneja en el servicio usando subcategorias ids
    Category toEntity(CategoryNewUpdateDTO dto);
    
    // Actualización de Entidad existente desde DTO
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "subcategorias", ignore = true)  // Se maneja en el servicio usando subcategorias ids
    void updateEntityFromDto(CategoryNewUpdateDTO dto, @MappingTarget Category entity);
}