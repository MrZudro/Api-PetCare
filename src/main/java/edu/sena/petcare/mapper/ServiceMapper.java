package edu.sena.petcare.mapper;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;
import edu.sena.petcare.dto.service.ServiceNewUpdateDTO;
import edu.sena.petcare.dto.service.ServiceReadDTO;
import edu.sena.petcare.models.Services;
import edu.sena.petcare.utility.ListaMappeo;

@Component
public class ServiceMapper {

    public ServiceReadDTO toDto(Services service) {
        if (service == null) {
            return null;
        }
        ServiceReadDTO dto = new ServiceReadDTO();
        dto.setId(service.getId());
        dto.setName(service.getName());
        dto.setDescription(service.getDescription());
        dto.setStatus(service.getStatus());
        dto.setPicture(service.getPicture());
        dto.setImageUrl(service.getPicture()); // Map picture to imageUrl for frontend

        // Extract clinics
        List<edu.sena.petcare.dto.veterinary.VeterinaryClinicSmallDTO> clinics = service
                .getVeterinaryClinicServices() == null
                        ? Collections.emptyList()
                        : service.getVeterinaryClinicServices().stream()
                                .map(vcs -> new edu.sena.petcare.dto.veterinary.VeterinaryClinicSmallDTO(
                                        vcs.getVeterinaryClinic().getId(),
                                        vcs.getVeterinaryClinic().getName()))
                                .toList();
        dto.setClinics(clinics);

        return dto;
    }

    public Services toEntity(ServiceNewUpdateDTO dto) {
        if (dto == null) {
            return null;
        }
        Services service = new Services();
        service.setName(dto.getName());
        service.setDescription(dto.getDescription());
        service.setStatus(dto.getStatus());
        service.setPicture(dto.getPicture());
        return service;
    }

    public void updateEntity(ServiceNewUpdateDTO dto, Services service) {
        if (dto == null || service == null) {
            return;
        }
        if (dto.getName() != null)
            service.setName(dto.getName());
        if (dto.getDescription() != null)
            service.setDescription(dto.getDescription());
        if (dto.getStatus() != null)
            service.setStatus(dto.getStatus());
        if (dto.getPicture() != null)
            service.setPicture(dto.getPicture());
    }

    public List<ServiceReadDTO> toDtoList(List<Services> entities) {
        return ListaMappeo.toDtoList(entities, this::toDto);
    }
}
