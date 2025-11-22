package edu.sena.petcare.services.service;

import edu.sena.petcare.dto.service.ServiceNewUpdateDTO;
import edu.sena.petcare.dto.service.ServiceReadDTO;
import edu.sena.petcare.exceptions.ResourceNotFoundException;
import edu.sena.petcare.mapper.ServiceMapper;
import edu.sena.petcare.models.Services;
import edu.sena.petcare.repositories.ServicesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;
import java.util.Objects;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicesServiceImpl implements ServicesService {

    private final ServicesRepository servicesRepository;
    private final ServiceMapper serviceMapper;

    @Override
    public ServiceReadDTO createService(ServiceNewUpdateDTO serviceNewUpdateDTO) {
        if (serviceNewUpdateDTO == null) {
            throw new IllegalArgumentException("serviceNewUpdateDTO es obligatorio");
        }
        Services service = serviceMapper.toEntity(serviceNewUpdateDTO);
        Services savedService = servicesRepository.save(service);
        return serviceMapper.toDto(savedService);
    }

    @Override
    public List<ServiceReadDTO> getAllServices() {
        List<Services> services = servicesRepository.findAll();
        return serviceMapper.toDtoList(services);
    }

    @Override
    public ServiceReadDTO getServiceById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id es obligatorio");
        }
        Services service = servicesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Servicio con id " + id + " no encontrado"));
        return serviceMapper.toDto(service);
    }

    @Override
    public ServiceReadDTO updateService(Long id, ServiceNewUpdateDTO serviceNewUpdateDTO) {
        if (id == null) {
            throw new IllegalArgumentException("id es obligatorio");
        }
        if (serviceNewUpdateDTO == null) {
            throw new IllegalArgumentException("serviceNewUpdateDTO es obligatorio");
        }
        Services serviceToUpdate = servicesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Servicio con id " + id + " no encontrado"));

        serviceMapper.updateEntity(serviceNewUpdateDTO, serviceToUpdate);
        Services updatedService = servicesRepository.save(serviceToUpdate);
        return serviceMapper.toDto(updatedService);
    }

    @Override
    public void deleteService(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id es obligatorio");
        }
        if (!servicesRepository.existsById(id)) {
            throw new ResourceNotFoundException("Servicio con id " + id + " no encontrado");
        }
        servicesRepository.deleteById(id);
    }

    @Override
    public List<ServiceReadDTO> getServicesByClinicId(Long clinicId) {
        if (clinicId == null) {
            throw new IllegalArgumentException("clinicId es obligatorio");
        }
        List<Services> services = servicesRepository.findServicesByClinicId(clinicId);
        return serviceMapper.toDtoList(services);
    }
}
