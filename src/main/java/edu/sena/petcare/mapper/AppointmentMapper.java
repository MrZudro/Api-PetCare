package edu.sena.petcare.mapper;

import edu.sena.petcare.dto.appointment.AppointmentNewUpdateDTO;
import edu.sena.petcare.dto.appointment.AppointmentReadDTO;
import edu.sena.petcare.models.Appointment;
import edu.sena.petcare.models.enums.AppointmentStatus;

public class AppointmentMapper {

    //-- Constructor privado para evitar instancias --
    private AppointmentMapper(){
        throw new IllegalStateException("Imposible instanciar");
    }
    //1. Entidad a DTO de lectura
    public static AppointmentReadDTO toReadDto(Appointment entity) {
        //Si el objeto entity que es una entidad de Appointment es nulo, retronamos un null por defecto
        if(entity == null){
            return null;
        }
        //Voy a crear un nuevo objeto del DTO de AppointmentReadDTO
        AppointmentReadDTO dto = new AppointmentReadDTO();
        dto.setId(entity.getId());
        dto.setAppointmentDateTime(entity.getAppointmentDateTime());
        dto.setStatus(entity.getStatus());
        dto.setReason(entity.getReason());

        //Voy a mapear id y nombres desde una entidad diferente, lo hago a traves de una sentencia condicional para 
        if(entity.getCustomer() != null){
            dto.setCustomerId(entity.getCustomer().getId());
            dto.setCustomerName(entity.getCustomer().getNames());
        }

        //Mapeo de la veterinaria
        if(entity.getVeterinaryClinic() != null){
            dto.setVeterinaryClinicName(entity.getVeterinaryClinic().getName());
        }

        //Mapeo del empleado por medio de la relation
        if(entity.getEmployee() != null){
            dto.setEmployeeName(entity.getEmployee().getNames());
        }

        return dto;
    } 

    // Si se requiere mapear una lista de este tipo use la utilidad ListaMappeo descrita en el Package Utility.

    //2. NewUpdateDTO a Entidad
    //Esto se emplea para crear o editar las citas
    public static Appointment toEntity(AppointmentNewUpdateDTO dto){
        //Primero hay que validad que nuestro querido dto no sea nulo
        if(dto == null){
            return null;
        }
        //Creamos el nuevo objeto de tipo Appointment

        Appointment entity = new Appointment();
        entity.setAppointmentDateTime(dto.getAppointmentDateTime());
        entity.setReason(dto.getReason());
        entity.setStatus(AppointmentStatus.PENDING);

        //No asignamos nada referente a Customer, VeterinaryClinic y Employee por el hecho de que se deben asignar en la capa de servicio
        return entity;
    }

    //3. Actualizacion de entidad
    public static void updateEntity(AppointmentNewUpdateDTO dto, Appointment entity){
        //Verificamos que ninguno de los dos vengan vacios, tanto el dto, como la entidad
        if(dto == null || entity == null){
            return;
        }

        //Actualizandin.. Metemos everything a traves de if para evitar la insercion de un null
        if(dto.getAppointmentDateTime() != null){
            entity.setAppointmentDateTime(dto.getAppointmentDateTime());
        }
        if(dto.getReason() != null){
            entity.setReason(dto.getReason());
        }
        if(dto.getStatus() != null){
            entity.setStatus(dto.getStatus());
        }

        //Si se requiere cambiar el id tanto de Customer, VeterinaryClinic y Employee se hara desde la capa de servicio
    }
}
