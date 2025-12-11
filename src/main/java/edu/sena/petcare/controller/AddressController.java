package edu.sena.petcare.controller;

import edu.sena.petcare.dto.address.AddressNewDTO;
import edu.sena.petcare.dto.address.AddressReadDTO;
import edu.sena.petcare.dto.address.AddressUpdateDTO;
import edu.sena.petcare.services.address.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping("/customers/{customerId}/addresses")
    public ResponseEntity<AddressReadDTO> createAddress(
            @PathVariable Long customerId,
            @Valid @RequestBody AddressNewDTO dto) {
        AddressReadDTO createdAddress = addressService.create(customerId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAddress);
    }

    @GetMapping("/customers/{customerId}/addresses")
    public ResponseEntity<List<AddressReadDTO>> getCustomerAddresses(@PathVariable Long customerId) {
        List<AddressReadDTO> addresses = addressService.findByCustomerId(customerId);
        return ResponseEntity.ok(addresses);
    }

    @GetMapping("/addresses/{id}")
    public ResponseEntity<AddressReadDTO> getAddressById(@PathVariable Long id) {
        AddressReadDTO address = addressService.findById(id);
        return ResponseEntity.ok(address);
    }

    @PutMapping("/addresses/{id}")
    public ResponseEntity<AddressReadDTO> updateAddress(
            @PathVariable Long id,
            @Valid @RequestBody AddressUpdateDTO dto) {
        AddressReadDTO updatedAddress = addressService.update(id, dto);
        return ResponseEntity.ok(updatedAddress);
    }

    @DeleteMapping("/addresses/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        addressService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/customers/{customerId}/addresses/{addressId}/set-default")
    public ResponseEntity<AddressReadDTO> setDefaultAddress(
            @PathVariable Long customerId,
            @PathVariable Long addressId) {
        AddressReadDTO address = addressService.setAsDefault(customerId, addressId);
        return ResponseEntity.ok(address);
    }
}
