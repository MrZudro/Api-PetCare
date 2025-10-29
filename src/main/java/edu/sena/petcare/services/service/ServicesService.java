package edu.sena.petcare.services.service;

import edu.sena.petcare.dto.service.ServiceNewUpdateDTO;
import edu.sena.petcare.dto.service.ServiceReadDTO;

import java.util.List;

public interface ServicesService {
    ServiceReadDTO createService(ServiceNewUpdateDTO serviceNewUpdateDTO);
    List<ServiceReadDTO> getAllServices();
    ServiceReadDTO getServiceById(Long id);
    ServiceReadDTO updateService(Long id, ServiceNewUpdateDTO serviceNewUpdateDTO);
    void deleteService(Long id);
    List<ServiceReadDTO> getServicesByClinicId(Long clinicId);
}
