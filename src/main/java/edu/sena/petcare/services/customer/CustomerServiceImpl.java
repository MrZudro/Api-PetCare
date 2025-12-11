package edu.sena.petcare.services.customer;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import java.util.Objects;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.sena.petcare.dto.customer.CustomerNewUpdateDTO;
import edu.sena.petcare.dto.customer.CustomerReadDTO;
import edu.sena.petcare.exceptions.ResourceNotFoundException;
import edu.sena.petcare.mapper.CustomerMapper;
import edu.sena.petcare.models.Customer;
import edu.sena.petcare.models.DocumentType;
import edu.sena.petcare.models.Neighborhood;
import edu.sena.petcare.models.Product;
import edu.sena.petcare.models.Wishlist;
import edu.sena.petcare.models.enums.Rol;
import edu.sena.petcare.repositories.CustomerRepository;
import edu.sena.petcare.repositories.DocumentTypeRepository;
import edu.sena.petcare.repositories.NeighborhoodRepository;
import edu.sena.petcare.repositories.WishlistRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final DocumentTypeRepository documentTypeRepository;
    private final NeighborhoodRepository neighborhoodRepository;
    private final WishlistRepository wishlistRepository;
    private final CustomerMapper customerMapper;
    private final PasswordEncoder passwordEncoder;

    private static final String ID_REQUIRED_MSG = "id es obligatorio";
    private static final String CUSTOMER_NOT_FOUND_MSG = "Cliente no encontrado con id: ";

    @Override
    @Transactional
    public CustomerReadDTO create(CustomerNewUpdateDTO dto) {
        Assert.notNull(dto, "dto es obligatorio");
        Customer entity = customerMapper.toEntity(dto);

        // Encrypt password before saving
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        DocumentType docType = documentTypeRepository
                .findById(java.util.Objects.requireNonNull(dto.getDocumentTypeId(), "documentTypeId es obligatorio"))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Tipo de documento no encontrado con id: " + dto.getDocumentTypeId()));
        entity.setDocumentType(docType);

        if (dto.getNeighborhoodId() != null) {
            Neighborhood barrio = neighborhoodRepository
                    .findById(java.util.Objects.requireNonNull(dto.getNeighborhoodId()))
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Barrio no encontrado con id: " + dto.getNeighborhoodId()));
            entity.setBarrioCliente(barrio);
        }

        entity.setRole(Rol.CUSTOMER);

        Customer saved = customerRepository.save(entity);

        // Crear wishlist inicial vacía no eliminable
        Wishlist wishlist = new Wishlist();
        wishlist.setUser(saved);
        wishlist.setCreateDate(LocalDateTime.now());
        wishlist.setProducts(java.util.Collections.<Product>emptyList());
        wishlistRepository.save(wishlist);

        return customerMapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerReadDTO> findAll() {
        return customerMapper.toDtoList(customerRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    @SuppressWarnings("null")
    public CustomerReadDTO findById(Long id) {
        Customer customer = customerRepository.findById(Objects.requireNonNull(id, ID_REQUIRED_MSG))
                .orElseThrow(() -> new ResourceNotFoundException(CUSTOMER_NOT_FOUND_MSG + id));
        return customerMapper.toDto(customer);
    }

    @Override
    @Transactional
    @SuppressWarnings("null")
    public CustomerReadDTO update(Long id, CustomerNewUpdateDTO dto) {
        Objects.requireNonNull(id, ID_REQUIRED_MSG);
        Objects.requireNonNull(dto, "dto es obligatorio");
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(CUSTOMER_NOT_FOUND_MSG + id));

        // Actualizamos campos simples
        customer.setNames(dto.getNames());
        customer.setLastNames(dto.getLastNames());
        customer.setDocumentNumber(dto.getDocumentNumber());
        customer.setEmail(dto.getEmail());

        // Only update password if provided - encrypt with BCrypt
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            customer.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        customer.setBirthDate(dto.getBirthDate());
        customer.setAddress(dto.getAddress());
        customer.setPhone(dto.getPhone());

        // Update profile photo URL if provided
        if (dto.getProfilePhotoUrl() != null && !dto.getProfilePhotoUrl().isBlank()) {
            customer.setProfilePhotoUrl(dto.getProfilePhotoUrl());
        }

        DocumentType docType = documentTypeRepository
                .findById(java.util.Objects.requireNonNull(dto.getDocumentTypeId(), "documentTypeId es obligatorio"))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Tipo de documento no encontrado con id: " + dto.getDocumentTypeId()));
        customer.setDocumentType(docType);

        if (dto.getNeighborhoodId() != null) {
            Neighborhood barrio = neighborhoodRepository
                    .findById(java.util.Objects.requireNonNull(dto.getNeighborhoodId()))
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Barrio no encontrado con id: " + dto.getNeighborhoodId()));
            customer.setBarrioCliente(barrio);
        } else {
            customer.setBarrioCliente(null);
        }

        Customer updated = customerRepository.save(customer);
        return customerMapper.toDto(updated);
    }

    @Override
    @Transactional
    @SuppressWarnings("null")
    public void delete(Long id) {
        Customer customer = customerRepository.findById(Objects.requireNonNull(id, ID_REQUIRED_MSG))
                .orElseThrow(() -> new ResourceNotFoundException(CUSTOMER_NOT_FOUND_MSG + id));
        // Soft delete: marcar isDeleted en User (no borrar tablas hijas)
        customer.setDeleted(true);
        customerRepository.save(customer);
    }

    @Override
    @Transactional
    public void changePassword(String email, String currentPassword, String newPassword) {
        var customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Verify current password
        if (!passwordEncoder.matches(currentPassword, customer.getPassword())) {
            throw new RuntimeException("La contraseña actual es incorrecta");
        }

        // Validate new password (at least 8 characters)
        if (newPassword.length() < 8) {
            throw new RuntimeException("La nueva contraseña debe tener al menos 8 caracteres");
        }

        // Update password
        customer.setPassword(passwordEncoder.encode(newPassword));
        customerRepository.save(customer);
    }
}
