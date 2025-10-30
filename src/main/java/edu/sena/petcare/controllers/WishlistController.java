package edu.sena.petcare.controllers;


import edu.sena.petcare.dto.wishlist.WishlistCreateDTO;
import edu.sena.petcare.dto.wishlist.WishlistNewUpdateDTO;
import edu.sena.petcare.dto.wishlist.WishlistReadDTO;
import edu.sena.petcare.services.wishlist.WishlistService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/wishlists")
@RequiredArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;

    @PostMapping
    public ResponseEntity<WishlistReadDTO> create(@Valid @RequestBody WishlistCreateDTO dto) {
        WishlistReadDTO createdWishlist = wishlistService.create(dto);
        return new ResponseEntity<>(createdWishlist, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<WishlistReadDTO>> getAll(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

        List<WishlistReadDTO> wishlists;
        if (startDate != null && endDate != null) {
            wishlists = wishlistService.findByDateRange(startDate, endDate);
        } else {
            wishlists = wishlistService.findAll();
        }
        return ResponseEntity.ok(wishlists);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WishlistReadDTO> getById(@PathVariable Long id) {
        WishlistReadDTO wishlist = wishlistService.findById(id);
        return ResponseEntity.ok(wishlist);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WishlistReadDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody WishlistNewUpdateDTO dto) {

        WishlistReadDTO updatedWishlist = wishlistService.update(id, dto);
        return ResponseEntity.ok(updatedWishlist);
    }

}
