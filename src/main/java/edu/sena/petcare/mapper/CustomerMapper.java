package edu.sena.petcare.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import edu.sena.petcare.dto.customer.CustomerNewUpdateDTO;
import edu.sena.petcare.dto.customer.CustomerReadDTO;
import edu.sena.petcare.models.Customer;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mappings({
            @Mapping(source = "documentType.id", target = "documentTypeId"),
            @Mapping(source = "barrioCliente.id", target = "neighborhoodId")
    })
    CustomerReadDTO toDto(Customer entity);

    List<CustomerReadDTO> toDtoList(List<Customer> entities);

    // Para creación, se administran relaciones en el servicio
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "role", ignore = true),
            @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "resetKey", ignore = true),
            @Mapping(target = "resetDate", ignore = true),
            @Mapping(target = "tokens", ignore = true),
            @Mapping(target = "documentType", ignore = true),
            @Mapping(target = "barrioCliente", ignore = true),
            @Mapping(target = "pets", ignore = true),
            @Mapping(target = "wishlists", ignore = true),
            @Mapping(target = "paymentMethods", ignore = true),
            @Mapping(target = "deleted", ignore = true)
    })
    Customer toEntity(CustomerNewUpdateDTO dto);
}


