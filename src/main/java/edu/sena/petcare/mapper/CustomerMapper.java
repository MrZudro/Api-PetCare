package edu.sena.petcare.mapper;

import org.springframework.stereotype.Component;
import edu.sena.petcare.dto.customer.CustomerNewUpdateDTO;
import edu.sena.petcare.dto.customer.CustomerReadDTO;
import edu.sena.petcare.models.Customer;
import edu.sena.petcare.utility.ListaMappeo;
import java.util.List;

@Component
public class CustomerMapper {

        public CustomerReadDTO toDto(Customer entity) {
                if (entity == null) {
                        return null;
                }
                CustomerReadDTO dto = new CustomerReadDTO();
                dto.setId(entity.getId());
                dto.setNames(entity.getNames());
                dto.setLastNames(entity.getLastNames());
                dto.setDocumentNumber(entity.getDocumentNumber());
                dto.setEmail(entity.getEmail());
                dto.setBirthDate(entity.getBirthDate());
                dto.setAddress(entity.getAddress());
                dto.setPhone(entity.getPhone());
                dto.setDocumentTypeId(entity.getDocumentType() != null ? entity.getDocumentType().getId() : null);
                dto.setNeighborhoodId(entity.getBarrioCliente() != null ? entity.getBarrioCliente().getId() : null);
                return dto;
        }

        public Customer toEntity(CustomerNewUpdateDTO dto) {
                if (dto == null) {
                        return null;
                }
                Customer entity = new Customer();
                entity.setNames(dto.getNames());
                entity.setLastNames(dto.getLastNames());
                entity.setDocumentNumber(dto.getDocumentNumber());
                entity.setEmail(dto.getEmail());
                entity.setPassword(dto.getPassword());
                entity.setBirthDate(dto.getBirthDate());
                entity.setAddress(dto.getAddress());
                entity.setPhone(dto.getPhone());
                // Relationships handled in Service
                return entity;
        }

        public void updateEntity(CustomerNewUpdateDTO dto, Customer entity) {
                if (dto == null || entity == null) {
                        return;
                }
                if (dto.getNames() != null)
                        entity.setNames(dto.getNames());
                if (dto.getLastNames() != null)
                        entity.setLastNames(dto.getLastNames());
                if (dto.getDocumentNumber() != null)
                        entity.setDocumentNumber(dto.getDocumentNumber());
                if (dto.getEmail() != null)
                        entity.setEmail(dto.getEmail());
                if (dto.getPassword() != null)
                        entity.setPassword(dto.getPassword());
                if (dto.getBirthDate() != null)
                        entity.setBirthDate(dto.getBirthDate());
                if (dto.getAddress() != null)
                        entity.setAddress(dto.getAddress());
                if (dto.getPhone() != null)
                        entity.setPhone(dto.getPhone());
                // Relationships handled in Service
        }

        public List<CustomerReadDTO> toDtoList(List<Customer> entities) {
                return ListaMappeo.toDtoList(entities, this::toDto);
        }
}
