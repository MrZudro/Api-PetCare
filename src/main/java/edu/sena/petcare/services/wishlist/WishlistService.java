package edu.sena.petcare.services.wishlist;

import edu.sena.petcare.dto.wishlist.WishlistCreateDTO;
import edu.sena.petcare.dto.wishlist.WishlistReadDTO;
import edu.sena.petcare.dto.wishlist.WishlistUpdateDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface WishlistService {

    WishlistReadDTO create(WishlistCreateDTO createDTO);

    List<WishlistReadDTO> findAll();

    WishlistReadDTO update(Long id, WishlistUpdateDTO updateDTO);

    WishlistReadDTO findById(Long id);



    void delete(Long id);

    // Método para la consulta personalizada
    List<WishlistReadDTO> findByDateRange(LocalDateTime startDate, LocalDateTime endDate);
}