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

    @Override
    @SuppressWarnings("null")
    public ServiceReadDTO createService(ServiceNewUpdateDTO serviceNewUpdateDTO) {
        Assert.notNull(serviceNewUpdateDTO, "serviceNewUpdateDTO es obligatorio");
        Services service = ServiceMapper.toEntity(serviceNewUpdateDTO);
        Services savedService = Objects.requireNonNull(servicesRepository.save(service));
        return ServiceMapper.toDto(savedService);
    }

    @Override
    public List<ServiceReadDTO> getAllServices() {
        List<Services> services = servicesRepository.findAll();
        return ServiceMapper.toDtoList(services);
    }

    @Override
    public ServiceReadDTO getServiceById(Long id) {
        Services service = servicesRepository.findById(Objects.requireNonNull(id, "id es obligatorio"))
                .orElseThrow(() -> new ResourceNotFoundException("Servicio con id " + id + " no encontrado"));
        return ServiceMapper.toDto(service);
    }

    @Override
    @SuppressWarnings("null")
    public ServiceReadDTO updateService(Long id, ServiceNewUpdateDTO serviceNewUpdateDTO) {
        Objects.requireNonNull(id, "id es obligatorio");
        Objects.requireNonNull(serviceNewUpdateDTO, "serviceNewUpdateDTO es obligatorio");
        Services serviceToUpdate = servicesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Servicio con id " + id + " no encontrado"));

        ServiceMapper.updateEntityFromDto(serviceNewUpdateDTO, serviceToUpdate);
        Services updatedService = servicesRepository.save(serviceToUpdate);
        return ServiceMapper.toDto(updatedService);
    }

    @Override
    public void deleteService(Long id) {
        if (!servicesRepository.existsById(Objects.requireNonNull(id, "id es obligatorio"))) {
            throw new ResourceNotFoundException("Servicio con id " + id + " no encontrado");
        }
        servicesRepository.deleteById(id);
    }

    @Override
    public List<ServiceReadDTO> getServicesByClinicId(Long clinicId) {
        List<Services> services = servicesRepository.findServicesByClinicId(Objects.requireNonNull(clinicId, "clinicId es obligatorio"));
        return ServiceMapper.toDtoList(services);
    }
}
