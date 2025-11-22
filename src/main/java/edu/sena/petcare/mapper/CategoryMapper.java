package edu.sena.petcare.mapper;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

import edu.sena.petcare.dto.category.CategoryNewUpdateDTO;
import edu.sena.petcare.dto.category.CategoryReadDTO;
import edu.sena.petcare.models.Category;
import edu.sena.petcare.models.Subcategory;
import edu.sena.petcare.utility.ListaMappeo;

// Mapper manual 
@Component
public class CategoryMapper {

    // 1. Mapeo de entidad a dto usando metodos de instancia
    public CategoryReadDTO toDto(Category entity) {
        // Validadmos que nuestra entidad no venga siendo null
        if (entity == null) {
            return null;
        }
        // Instanciamos el nuevo dto que sontendra los datos
        CategoryReadDTO dto = new CategoryReadDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());

        // Mapeo de subcategorias a una lista de IDs
        List<Long> subcategoryIds = entity.getSubcategorias() == null
                ? Collections.emptyList()
                : entity.getSubcategorias().stream()
                        .map(Subcategory::getId)
                        .toList();

        dto.setSubcategoriaIds((subcategoryIds));

        return dto;
    }

    //Mapeo de DTO a entidad
    public Category toEntity(CategoryNewUpdateDTO dto){
        //validamos que el dto no venga vacio pa no perder tiempo y recursos
        if(dto == null){
            return null;
        }
        
        //Instanciamos el objeto vacio que retornaremos con la info del DTO
        Category entity = new Category();

        entity.setName(dto.getName());
        //El paso siguiente serian las listas con las subcategorias pero francamente las vamos a ignorar ya que estas deberian estar en la capa de servicio

        return entity;
    }

    //Actualizacion de una entidad por medio de un dto
    public void updateEntity(CategoryNewUpdateDTO dto, Category entity){
        //Validamos, siempre validamos 
        if(dto == null ||entity == null){
            return;
        }

        //Actualizaremos el nombre si y solo si esta en presente en el DTO
        if(dto.getName() != null){
            entity.setName(dto.getName());
        }

        //La actualizacion de las subcategorias debe hacerse siempre desde la capa de servicio pues hay que buscar y gestionar los cambios en as referencias de la base de datos
    }

    //Mapeo de lista de entidades usando la utilidad ListMappeo
    public List<CategoryReadDTO> toDtoList(List<Category> entities){
        return ListaMappeo.toDtoList(entities, this::toDto);
    }
}