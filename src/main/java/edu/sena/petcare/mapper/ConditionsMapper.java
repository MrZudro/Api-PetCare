package edu.sena.petcare.mapper;

import edu.sena.petcare.dto.condition.ConditionsDTO;
import edu.sena.petcare.models.Conditions;

public class ConditionsMapper {

    private ConditionsMapper(){}

    public static ConditionsDTO toDTO(Conditions conditions){
        if(conditions == null){
            return null;
        } 

        ConditionsDTO dto = new ConditionsDTO();

        //Mapeo simple
        dto.setName(conditions.getName());
        dto.setDescription(conditions.getDescription());
        dto.setIcon(conditions.getIcon());

        //retornamos el dto que ha sido setteado
        return dto;
    }

    public static Conditions toEntity(ConditionsDTO dto){
        if(dto == null){
            return null;
        }

        //Creamos una entidad vacia y nuevecita
        Conditions nueva = new Conditions();

        //Mapeo simple
        nueva.setName(dto.getName());
        nueva.setDescription(dto.getDescription());
        nueva.setIcon(dto.getIcon());
        nueva.setId(null);

        //retornamos la entidad que ha sido setteada
        return nueva;
    }

    public static void updateEntity(ConditionsDTO dto, Conditions conditions){
        if(dto == null || conditions == null){
            return;
        }

        //Mapeo simple
        conditions.setName(dto.getName());
        conditions.setDescription(dto.getDescription());
        conditions.setIcon(dto.getIcon());
    }
}
