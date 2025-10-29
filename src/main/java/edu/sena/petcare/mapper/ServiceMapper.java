package edu.sena.petcare.mapper;

import edu.sena.petcare.dto.service.ServiceNewUpdateDTO;
import edu.sena.petcare.dto.service.ServiceReadDTO;
import edu.sena.petcare.models.Services;

import java.util.List;
import java.util.stream.Collectors;

public class ServiceMapper {

    public static ServiceReadDTO toDto(Services service) {
        if (service == null) {
            return null;
        }
        ServiceReadDTO dto = new ServiceReadDTO();
        dto.setId(service.getId());
        dto.setName(service.getName());
        dto.setDescription(service.getDescription());
        dto.setStatus(service.getStatus());
        dto.setPicture(service.getPicture());
        return dto;
    }

    public static List<ServiceReadDTO> toDtoList(List<Services> services) {
        if (services == null) {
            return null;
        }
        return services.stream()
                .map(ServiceMapper::toDto)
                .collect(Collectors.toList());
    }

    public static Services toEntity(ServiceNewUpdateDTO dto) {
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

    public static void updateEntityFromDto(ServiceNewUpdateDTO dto, Services service) {
        if (dto == null || service == null) {
            return;
        }
        service.setName(dto.getName());
        service.setDescription(dto.getDescription());
        service.setStatus(dto.getStatus());
        service.setPicture(dto.getPicture());
    }
}
