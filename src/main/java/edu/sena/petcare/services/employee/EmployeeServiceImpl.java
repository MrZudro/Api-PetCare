package edu.sena.petcare.services.employee;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import java.util.Objects;
import org.springframework.transaction.annotation.Transactional;

import edu.sena.petcare.dto.employee.EmployeeNewUpdateDTO;
import edu.sena.petcare.dto.employee.EmployeeReadDTO;
import edu.sena.petcare.exceptions.ResourceNotFoundException;
import edu.sena.petcare.mapper.EmployeeMapper;
import edu.sena.petcare.models.DocumentType;
import edu.sena.petcare.models.Employee;
import edu.sena.petcare.models.Neighborhood;
import edu.sena.petcare.models.enums.EmployeeCargo;
import edu.sena.petcare.models.enums.Rol;
import edu.sena.petcare.repositories.DocumentTypeRepository;
import edu.sena.petcare.repositories.EmployeeRepository;
import edu.sena.petcare.repositories.NeighborhoodRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DocumentTypeRepository documentTypeRepository;
    private final NeighborhoodRepository neighborhoodRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    @Transactional
    public EmployeeReadDTO create(EmployeeNewUpdateDTO dto) {
        Assert.notNull(dto, "dto es obligatorio");
        Employee entity = employeeMapper.toEntity(dto);

        DocumentType docType = documentTypeRepository
                .findById(Objects.requireNonNull(dto.getDocumentTypeId(), "documentTypeId es obligatorio"))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Tipo de documento no encontrado con id: " + dto.getDocumentTypeId()));
        entity.setDocumentType(docType);

        if (dto.getNeighborhoodId() != null) {
            Neighborhood barrio = neighborhoodRepository.findById(Objects.requireNonNull(dto.getNeighborhoodId()))
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Barrio no encontrado con id: " + dto.getNeighborhoodId()));
            entity.setBarrioCliente(barrio);
        }

        entity.setRole(Rol.EMPLOYEE);

        Employee saved = employeeRepository.save(entity);
        return employeeMapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeReadDTO> findAll() {
        return employeeMapper.toDtoList(employeeRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    @SuppressWarnings("null")
    public EmployeeReadDTO findById(Long id) {
        Employee employee = employeeRepository.findById(Objects.requireNonNull(id, "id es obligatorio"))
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado con id: " + id));
        return employeeMapper.toDto(employee);
    }

    @Override
    @Transactional
    @SuppressWarnings("null")
    public EmployeeReadDTO update(Long id, EmployeeNewUpdateDTO dto) {
        Objects.requireNonNull(id, "id es obligatorio");
        Objects.requireNonNull(dto, "dto es obligatorio");
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado con id: " + id));

        employee.setNames(dto.getNames());
        employee.setLastNames(dto.getLastNames());
        employee.setDocumentNumber(dto.getDocumentNumber());
        employee.setEmail(dto.getEmail());
        employee.setPassword(dto.getPassword());
        employee.setBirthDate(dto.getBirthDate());

        employee.setPhone(dto.getPhone());

        employee.setEmployeeCode(dto.getEmployeeCode());
        employee.setSalary(dto.getSalary());
        employee.setCargo(EmployeeCargo.valueOf(dto.getCargo()));

        DocumentType docType = documentTypeRepository.findById(dto.getDocumentTypeId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Tipo de documento no encontrado con id: " + dto.getDocumentTypeId()));
        employee.setDocumentType(docType);

        if (dto.getNeighborhoodId() != null) {
            Neighborhood barrio = neighborhoodRepository.findById(dto.getNeighborhoodId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Barrio no encontrado con id: " + dto.getNeighborhoodId()));
            employee.setBarrioCliente(barrio);
        } else {
            employee.setBarrioCliente(null);
        }

        Employee updated = employeeRepository.save(employee);
        return employeeMapper.toDto(updated);
    }

    @Override
    @Transactional
    @SuppressWarnings("null")
    public void delete(Long id) {
        Employee employee = employeeRepository.findById(Objects.requireNonNull(id, "id es obligatorio"))
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado con id: " + id));
        employee.setDeleted(true);
        employeeRepository.save(employee);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeReadDTO> findByVeterinaryClinicId(Long veterinaryClinicId) {
        if (veterinaryClinicId == null) {
            throw new IllegalArgumentException("veterinaryClinicId cannot be null");
        }
        List<Employee> employees = employeeRepository.findByVeterinaryClinicId(veterinaryClinicId);
        return employeeMapper.toDtoList(employees);
    }
}
