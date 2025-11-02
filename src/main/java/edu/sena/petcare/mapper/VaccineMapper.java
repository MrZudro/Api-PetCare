package edu.sena.petcare.mapper;

import edu.sena.petcare.dto.Vaccine.VaccineCreateDTO;
import edu.sena.petcare.dto.Vaccine.VaccineReadDTO;
import edu.sena.petcare.dto.Vaccine.VaccineUpdateDTO;
import edu.sena.petcare.models.Vaccine;
import java.time.LocalDate;

public class VaccineMapper {

    public static Vaccine toEntity(VaccineCreateDTO dto) {
        Vaccine v = new Vaccine();
        v.setNombre(dto.getNombre());
        v.setDescripcion(dto.getDescripcion());
        v.setFechaAplicacion(LocalDate.parse(dto.getFechaAplicacion()));
        v.setActiva(true);
        return v;
    }

    public static VaccineReadDTO toReadDTO(Vaccine entity) {
        return new VaccineReadDTO(
                entity.getId(),
                entity.getNombre(),
                entity.getDescripcion(),
                entity.getFechaAplicacion().toString(),
                entity.isActiva()
        );
    }

    public static void updateEntity(Vaccine entity, VaccineUpdateDTO dto) {
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setFechaAplicacion(LocalDate.parse(dto.getFechaAplicacion()));
        entity.setActiva(dto.isActiva());
    }
}