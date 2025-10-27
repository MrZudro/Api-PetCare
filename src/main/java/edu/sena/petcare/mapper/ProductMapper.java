package edu.sena.petcare.mapper;

import java.util.List;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import edu.sena.petcare.dto.product.ProductNewUpdateDTO;
import edu.sena.petcare.dto.product.ProductReadDTO;
import edu.sena.petcare.models.Product;


@Mapper
public interface ProductMapper {

    //  0. Obtencion del mapper en tiempo de compilacion, tipo Standalone
    ProductMapper mapper = Mappers.getMapper(ProductMapper.class);
    
    // 1. Mapeo de Entidad a DTO de Lectura
    @Mapping(target = "subcategoriesIds", expression = "java(entity.getCategories().stream().map(cat -> cat.getId()).toList())")
    ProductReadDTO toDto(Product entity);
    List<ProductReadDTO> toDtoList(List<Product> entities);

    // 2. Mapeo de DTO de Solicitud a Entidad (para CREAR/ACTUALIZAR)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categories", ignore = true)  // Se maneja en el servicio
    @Mapping(target = "billDetails", ignore = true) // Inicialmente vacío
    @Mapping(target = "wishlists", ignore = true)   // Inicialmente vacío
    @Mapping(target = "isActive", constant = "ACTIVE")  // Nuevo producto siempre activo
    Product toEntity(ProductNewUpdateDTO dto);
    
    // 3. Método para actualizar una Entidad existente
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categories", ignore = true)  // Se maneja en el servicio
    @Mapping(target = "billDetails", ignore = true) // No se modifica
    @Mapping(target = "wishlists", ignore = true)   // No se modifica
    void updateEntityFromDto(ProductNewUpdateDTO dto, @MappingTarget Product entity);
}