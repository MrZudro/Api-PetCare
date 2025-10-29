package edu.sena.petcare.services.service;

import edu.sena.petcare.dto.service.ServiceNewUpdateDTO;
import edu.sena.petcare.dto.service.ServiceReadDTO;
import edu.sena.petcare.exceptions.ResourceNotFoundException;
import edu.sena.petcare.mapper.ServiceMapper;
import edu.sena.petcare.models.Services;
import edu.sena.petcare.repositories.ServicesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicesServiceImpl implements ServicesService {

    private final ServicesRepository servicesRepository;

    @Override
    public ServiceReadDTO createService(ServiceNewUpdateDTO serviceNewUpdateDTO) {
        Services service = ServiceMapper.toEntity(serviceNewUpdateDTO);
        Services savedService = servicesRepository.save(service);
        return ServiceMapper.toDto(savedService);
    }

    @Override
    public List<ServiceReadDTO> getAllServices() {
        List<Services> services = servicesRepository.findAll();
        return ServiceMapper.toDtoList(services);
    }

    @Override
    public ServiceReadDTO getServiceById(Long id) {
        Services service = servicesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Servicio con id " + id + " no encontrado"));
        return ServiceMapper.toDto(service);
    }

    @Override
    public ServiceReadDTO updateService(Long id, ServiceNewUpdateDTO serviceNewUpdateDTO) {
        Services serviceToUpdate = servicesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Servicio con id " + id + " no encontrado"));

        ServiceMapper.updateEntityFromDto(serviceNewUpdateDTO, serviceToUpdate);
        Services updatedService = servicesRepository.save(serviceToUpdate);
        return ServiceMapper.toDto(updatedService);
    }

    @Override
    public void deleteService(Long id) {
        if (!servicesRepository.existsById(id)) {
            throw new ResourceNotFoundException("Servicio con id " + id + " no encontrado");
        }
        servicesRepository.deleteById(id);
    }

    @Override
    public List<ServiceReadDTO> getServicesByClinicId(Long clinicId) {
        List<Services> services = servicesRepository.findServicesByClinicId(clinicId);
        return ServiceMapper.toDtoList(services);
    }
}
