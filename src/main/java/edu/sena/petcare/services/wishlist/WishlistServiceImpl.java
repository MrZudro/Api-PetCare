package edu.sena.petcare.services.wishlist;

import edu.sena.petcare.dto.wishlist.WishlistNewUpdateDTO;
import edu.sena.petcare.dto.wishlist.WishlistReadDTO;
import edu.sena.petcare.exceptions.ResourceNotFoundException;
import edu.sena.petcare.exceptions.BadRequestException;
import edu.sena.petcare.mapper.WishlistMapper;
import edu.sena.petcare.models.Product;
import edu.sena.petcare.models.User;
import edu.sena.petcare.models.Wishlist;
import edu.sena.petcare.repositories.ProductRepository;
import edu.sena.petcare.repositories.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;
    private final ProductRepository productRepository;
    private final WishlistMapper wishlistMapper;

    @Override
    @Transactional(readOnly = true)
    public List<WishlistReadDTO> findAll() {
        return wishlistRepository.findAll()
                .stream()
                .map(wishlistMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public WishlistReadDTO findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id es obligatorio");
        }
        Wishlist wishlist = wishlistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Wishlist no encontrada con id: " + id));
        return wishlistMapper.toDto(wishlist);
    }

    @Override
    @Transactional
    public WishlistReadDTO create(WishlistNewUpdateDTO wishlistDTO) {
        if (wishlistDTO == null) {
            throw new IllegalArgumentException("dto es obligatorio");
        }
        // La creación manual de wishlists no está permitida; se crea automáticamente al
        // crear un Customer
        throw new BadRequestException(
                "La wishlist se crea automáticamente al registrar un cliente y no puede crearse manualmente.");
    }

    @Override
    @Transactional
    public WishlistReadDTO update(Long id, WishlistNewUpdateDTO wishlistDTO) {
        if (id == null) {
            throw new IllegalArgumentException("id es obligatorio");
        }
        if (wishlistDTO == null) {
            throw new IllegalArgumentException("dto es obligatorio");
        }
        // 1. Encontrar la Wishlist existente
        Wishlist wishlist = wishlistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Wishlist no encontrada con id: " + id));

        // 2. NO se permite cambiar el usuario propietario. Se ignora el userId del DTO.
        User user = wishlist.getUser();

        // 3. Validar Productos nuevos
        List<Long> productIds = wishlistDTO.getProductIds() == null ? Collections.emptyList()
                : wishlistDTO.getProductIds();
        List<Product> newProducts = productRepository.findAllById(productIds);
        if (newProducts.size() != productIds.size()) {
            throw new ResourceNotFoundException("Uno o más productos no fueron encontrados.");
        }

        // 4. Desvincular productos antiguos (Lógica ManyToMany)
        for (Product oldProduct : wishlist.getProducts()) {
            oldProduct.getWishlists().remove(wishlist);
        }
        wishlist.getProducts().clear();

        // 5. Vincular productos nuevos (puede ser lista vacía)
        for (Product newProduct : newProducts) {
            wishlist.getProducts().add(newProduct);
            newProduct.getWishlists().add(wishlist);
        }

        wishlist.setUser(user);
        // No actualizamos la fecha de creación (createDate)

        Wishlist updatedWishlist = wishlistRepository.save(wishlist);
        return wishlistMapper.toDto(updatedWishlist);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id es obligatorio");
        }
        // No se permite eliminar la wishlist
        throw new BadRequestException("La wishlist no puede ser eliminada. Solo puede ser editada.");
    }

    @Override
    @Transactional(readOnly = true)
    public List<WishlistReadDTO> findByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return wishlistRepository.findByCreateDateBetween(startDate, endDate)
                .stream()
                .map(wishlistMapper::toDto)
                .collect(Collectors.toList());
    }
}

// ya se que no existe un CRUD de user pero es por ahora como para simular
// cuando este implementado