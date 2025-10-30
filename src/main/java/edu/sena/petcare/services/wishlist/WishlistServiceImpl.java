package edu.sena.petcare.services.wishlist;

import edu.sena.petcare.dto.wishlist.WishlistCreateDTO;
import edu.sena.petcare.dto.wishlist.WishlistNewUpdateDTO;
import edu.sena.petcare.dto.wishlist.WishlistReadDTO;
import edu.sena.petcare.dto.wishlist.WishlistUpdateDTO;
import edu.sena.petcare.exceptions.DuplicateResourceException;
import edu.sena.petcare.exceptions.ResourceNotFoundException;
import edu.sena.petcare.mapper.WishlistMapper;
import edu.sena.petcare.models.Product;
import edu.sena.petcare.models.User;
import edu.sena.petcare.models.Wishlist;
import edu.sena.petcare.repositories.ProductRepository;
import edu.sena.petcare.repositories.UserRepository;
import edu.sena.petcare.repositories.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final WishlistMapper wishlistMapper;

    @Override
    @Transactional(readOnly = true)
    public List<WishlistReadDTO> findAll() {
        return wishlistRepository.findAll()
                .stream()
                .map(wishlistMapper::toReadDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public WishlistReadDTO findById(Long id) {
        Wishlist wishlist = wishlistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Wishlist no encontrada con id: " + id));
        return wishlistMapper.toReadDTO(wishlist);
    }

    @Override
    @Transactional
    public WishlistReadDTO create(WishlistCreateDTO createDTO) {
    // REGLA 1: Verificar si el usuario ya tiene una wishlist
        if(wishlistRepository.existsByUserId(createDTO.getUserId())) {
            throw new DuplicateResourceException("El usuario con id " + createDTO.getUserId() + " ya tiene una wishlist.");
        }

        // 1. Validar User
        User user = userRepository.findById(createDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + createDTO.getUserId()));

        Wishlist wishlist = new Wishlist();
        wishlist.setUser(user);
        wishlist.setCreateDate(LocalDateTime.now());

        // REGLA 2: Aceptar lista vacía
        // Verificamos si la lista NO es nula y NO está vacía antes de buscar productos
        if (createDTO.getProductIds() != null && !createDTO.getProductIds().isEmpty()) {
            List<Product> products = productRepository.findAllById(createDTO.getProductIds());
            if (products.size() != createDTO.getProductIds().size()) {
                throw new ResourceNotFoundException("Uno o más productos no fueron encontrados.");
            }
            wishlist.setProducts(products);

            // Actualizar el lado "dueño" (Product)
            for (Product product : products) {
                product.getWishlists().add(wishlist);
            }
        } else {
            // Si la lista es nula o vacía, solo asigna una lista vacía
            wishlist.setProducts(new java.util.ArrayList<>());
        }

        Wishlist savedWishlist = wishlistRepository.save(wishlist);
        return wishlistMapper.toReadDTO(savedWishlist);
    }

    @Override
    @Transactional
    public WishlistReadDTO update(Long id, WishlistUpdateDTO updateDTO) {
        // 1. Encontrar la Wishlist existente
        Wishlist wishlist = wishlistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Wishlist no encontrada con id: " + id));

        // 2. Validar User
        User user = userRepository.findById(wishlistDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + wishlistDTO.getUserId()));

        // 3. Validar Productos nuevos
        List<Product> newProducts = productRepository.findAllById(updateDTO.getProductIds());
        if (!updateDTO.getProductIds().isEmpty() && newProducts.size() != updateDTO.getProductIds().size()) {
            throw new ResourceNotFoundException("Uno o más productos no fueron encontrados.");
        }

        // 4. Desvincular productos antiguos (Lógica ManyToMany)
        for (Product oldProduct : wishlist.getProducts()) {
            oldProduct.getWishlists().remove(wishlist);
        }
        wishlist.getProducts().clear();

        // 5. Vincular productos nuevos (si la lista no está vacía)
        if (!newProducts.isEmpty()) {
            for (Product newProduct : newProducts) {
                wishlist.getProducts().add(newProduct);
                newProduct.getWishlists().add(wishlist);
            }
        }

        wishlist.setUser(user);
        // No actualizamos la fecha de creación (createDate)

        Wishlist updatedWishlist = wishlistRepository.save(wishlist);
        return wishlistMapper.toReadDTO(updatedWishlist);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Wishlist wishlist = wishlistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Wishlist no encontrada con id: " + id));

        // Desvincular de los productos antes de borrar
        for (Product product : wishlist.getProducts()) {
            product.getWishlists().remove(wishlist);
        }

        wishlistRepository.delete(wishlist);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WishlistReadDTO> findByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return wishlistRepository.findByCreateDateBetween(startDate, endDate)
                .stream()
                .map(wishlistMapper::toReadDTO)
                .collect(Collectors.toList());
    }
}

//ya se que no existe un CRUD de user pero es por ahora como para simular cuando este implementado