package edu.sena.petcare.controllers;

import edu.sena.petcare.dto.product.ProductNewUpdateDTO;
import edu.sena.petcare.dto.product.ProductReadDTO;
import edu.sena.petcare.services.product.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductReadDTO> createProduct(@Valid @RequestBody ProductNewUpdateDTO productNewUpdateDTO) {
        ProductReadDTO createdProduct = productService.nuevoProdcuto(productNewUpdateDTO);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductReadDTO>> getAllProducts() {
        List<ProductReadDTO> products = productService.todosProductos();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductReadDTO> getProductById(@PathVariable Long id) {
        ProductReadDTO product = productService.unProductoEspecifico(id);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductNewUpdateDTO> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductNewUpdateDTO productNewUpdateDTO) {
        ProductNewUpdateDTO updatedProduct = productService.actualizarProducto(id, productNewUpdateDTO);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.borrarProducto(id);
        return ResponseEntity.noContent().build();
    }
}
