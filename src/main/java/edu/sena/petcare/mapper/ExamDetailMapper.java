package edu.sena.petcare.mapper;


import edu.sena.petcare.dto.examdetail.ExamDetailNewUpdateDTO;
import edu.sena.petcare.dto.examdetail.ExamDetailReadDTO;
import edu.sena.petcare.models.ExamDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ExamDetailMapper {

    // 1. Entidad -> ReadDTO
    @Mapping(source = "exam.id", target = "examId")
    @Mapping(source = "pet.id", target = "petId")
    @Mapping(source = "employee.id", target = "employeeId")
    ExamDetailReadDTO toReadDTO(ExamDetail examDetail);

    // 2. NewUpdateDTO -> Entidad
    // Mapeo inverso no es necesario aquí, lo haremos en el servicio

    // 3. Update DTO -> Entidad (para actualizar)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "exam", ignore = true)
    @Mapping(target = "pet", ignore = true)
    @Mapping(target = "employee", ignore = true)
    @Mapping(target = "examDate", source = "examDate") // Si es null, MapStruct lo ignorará
    void updateEntityFromDto(ExamDetailNewUpdateDTO dto, @MappingTarget ExamDetail examDetail);
}