package edu.sena.petcare.services.address;

import edu.sena.petcare.dto.address.AddressNewDTO;
import edu.sena.petcare.dto.address.AddressReadDTO;
import edu.sena.petcare.dto.address.AddressUpdateDTO;
import edu.sena.petcare.models.Address;
import edu.sena.petcare.models.Customer;
import edu.sena.petcare.models.Neighborhood;
import edu.sena.petcare.repositories.AddressRepository;
import edu.sena.petcare.repositories.CustomerRepository;
import edu.sena.petcare.repositories.NeighborhoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;
    private final NeighborhoodRepository neighborhoodRepository;

    @Override
    @Transactional
    public AddressReadDTO create(Long customerId, AddressNewDTO dto) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + customerId));

        Neighborhood neighborhood = neighborhoodRepository.findById(dto.getNeighborhoodId())
                .orElseThrow(() -> new RuntimeException("Barrio no encontrado con id: " + dto.getNeighborhoodId()));

        // Si esta dirección se marca como predeterminada, desmarcar la anterior
        if (dto.getIsDefault() != null && dto.getIsDefault()) {
            addressRepository.findByCustomerIdAndIsDefaultTrueAndIsActiveTrue(customerId)
                    .ifPresent(existingDefault -> {
                        existingDefault.setIsDefault(false);
                        addressRepository.save(existingDefault);
                    });
        }

        Address address = toEntity(dto, customer, neighborhood);
        Address savedAddress = addressRepository.save(address);

        return toDto(savedAddress);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AddressReadDTO> findByCustomerId(Long customerId) {
        return addressRepository.findByCustomerIdAndIsActiveTrue(customerId)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public AddressReadDTO findById(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dirección no encontrada con id: " + id));
        return toDto(address);
    }

    @Override
    @Transactional
    public AddressReadDTO update(Long id, AddressUpdateDTO dto) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dirección no encontrada con id: " + id));

        Neighborhood neighborhood = neighborhoodRepository.findById(dto.getNeighborhoodId())
                .orElseThrow(() -> new RuntimeException("Barrio no encontrado con id: " + dto.getNeighborhoodId()));

        // Si esta dirección se marca como predeterminada, desmarcar la anterior
        if (dto.getIsDefault() != null && dto.getIsDefault() && !address.getIsDefault()) {
            addressRepository.findByCustomerIdAndIsDefaultTrueAndIsActiveTrue(address.getCustomer().getId())
                    .ifPresent(existingDefault -> {
                        existingDefault.setIsDefault(false);
                        addressRepository.save(existingDefault);
                    });
        }

        updateEntity(address, dto, neighborhood);
        Address updatedAddress = addressRepository.save(address);

        return toDto(updatedAddress);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dirección no encontrada con id: " + id));

        // No permitir eliminar la dirección predeterminada si es la única activa
        if (address.getIsDefault()) {
            long activeAddressCount = addressRepository.findByCustomerIdAndIsActiveTrue(address.getCustomer().getId())
                    .size();
            if (activeAddressCount <= 1) {
                throw new RuntimeException("No se puede eliminar la única dirección activa");
            }
        }

        address.setIsActive(false);
        addressRepository.save(address);
    }

    @Override
    @Transactional
    public AddressReadDTO setAsDefault(Long customerId, Long addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Dirección no encontrada con id: " + addressId));

        if (!address.getCustomer().getId().equals(customerId)) {
            throw new RuntimeException("La dirección no pertenece al cliente especificado");
        }

        // Desmarcar la dirección predeterminada anterior
        addressRepository.findByCustomerIdAndIsDefaultTrueAndIsActiveTrue(customerId)
                .ifPresent(existingDefault -> {
                    existingDefault.setIsDefault(false);
                    addressRepository.save(existingDefault);
                });

        // Marcar esta dirección como predeterminada
        address.setIsDefault(true);
        address.setUpdatedDate(Instant.now());
        Address updatedAddress = addressRepository.save(address);

        return toDto(updatedAddress);
    }

    // ============ MÉTODOS DE MAPEO MANUAL ============

    private AddressReadDTO toDto(Address address) {
        AddressReadDTO dto = new AddressReadDTO();
        dto.setId(address.getId());
        dto.setAddressLine(address.getAddressLine());
        dto.setAdditionalInfo(address.getAdditionalInfo());
        dto.setDeliveryNotes(address.getDeliveryNotes());
        dto.setAddressType(address.getAddressType());
        dto.setIsDefault(address.getIsDefault());
        dto.setCreatedDate(address.getCreatedDate());
        dto.setUpdatedDate(address.getUpdatedDate());

        if (address.getNeighborhood() != null) {
            dto.setNeighborhoodId(address.getNeighborhood().getId());
            dto.setNeighborhoodName(address.getNeighborhood().getName());

            if (address.getNeighborhood().getLocality() != null) {
                dto.setLocalityId(address.getNeighborhood().getLocality().getId());
                dto.setLocalityName(address.getNeighborhood().getLocality().getName());
            }
        }

        return dto;
    }

    private Address toEntity(AddressNewDTO dto, Customer customer, Neighborhood neighborhood) {
        Address address = new Address();
        address.setAddressLine(dto.getAddressLine());
        address.setAdditionalInfo(dto.getAdditionalInfo());
        address.setDeliveryNotes(dto.getDeliveryNotes());
        address.setAddressType(dto.getAddressType());
        address.setIsDefault(dto.getIsDefault() != null ? dto.getIsDefault() : false);
        address.setIsActive(true);
        address.setCreatedDate(Instant.now());
        address.setUpdatedDate(Instant.now());
        address.setCustomer(customer);
        address.setNeighborhood(neighborhood);

        return address;
    }

    private void updateEntity(Address address, AddressUpdateDTO dto, Neighborhood neighborhood) {
        address.setAddressLine(dto.getAddressLine());
        address.setAdditionalInfo(dto.getAdditionalInfo());
        address.setDeliveryNotes(dto.getDeliveryNotes());
        address.setAddressType(dto.getAddressType());
        address.setIsDefault(dto.getIsDefault() != null ? dto.getIsDefault() : address.getIsDefault());
        address.setUpdatedDate(Instant.now());
        address.setNeighborhood(neighborhood);
    }
}
