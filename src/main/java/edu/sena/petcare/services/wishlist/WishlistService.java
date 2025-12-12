package edu.sena.petcare.services.wishlist;

import edu.sena.petcare.dto.wishlist.WishlistNewUpdateDTO;
import edu.sena.petcare.dto.wishlist.WishlistReadDTO;
import java.time.LocalDateTime;
import java.util.List;

public interface WishlistService {

    WishlistReadDTO create(WishlistNewUpdateDTO wishlistDTO);

    List<WishlistReadDTO> findAll();

    WishlistReadDTO update(Long id, WishlistNewUpdateDTO wishlistDTO);

    WishlistReadDTO findById(Long id);

    void delete(Long id);

    // Método para la consulta personalizada
    List<WishlistReadDTO> findByDateRange(LocalDateTime startDate, LocalDateTime endDate);

    WishlistReadDTO findByUserId(Long userId);

    WishlistReadDTO addProduct(Long userId, Long productId);

    WishlistReadDTO removeProduct(Long userId, Long productId);
}