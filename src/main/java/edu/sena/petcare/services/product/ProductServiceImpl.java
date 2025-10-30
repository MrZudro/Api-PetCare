package edu.sena.petcare.services.product;

import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;
import java.util.Objects;

import edu.sena.petcare.dto.product.ProductNewUpdateDTO;
import edu.sena.petcare.dto.product.ProductReadDTO;
import edu.sena.petcare.exceptions.BadRequestException;
import edu.sena.petcare.exceptions.DuplicateResourceException;
import edu.sena.petcare.exceptions.ResourceNotFoundException;
import edu.sena.petcare.mapper.ProductMapper;
import edu.sena.petcare.models.Product;
import edu.sena.petcare.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    @SuppressWarnings("null")
    public ProductReadDTO nuevoProdcuto(ProductNewUpdateDTO nuevoProductoDTO)  {
        //Verifico primero que no haya un producto igual por medio del SKU
        Optional<Product> existeProducto = productRepository.findBySkuIgnoreCase(nuevoProductoDTO.getSku());
        if(existeProducto.isPresent()){
            throw new DuplicateResourceException("El producto ingresado con SKU " + nuevoProductoDTO.getName()+ " ya existe!");
        }
        

        //Verifico que tenga una o mas subcategorias
        if(nuevoProductoDTO.getSubcategoriaIds() == null || nuevoProductoDTO.getSubcategoriaIds().isEmpty()){
            throw new BadRequestException("Un producto debe tener al menos una subcategoria");
        }

        //Mapeo a entidad usando MapStruct
        Product productillo = productMapper.toEntity(nuevoProductoDTO);

        //Guardo la entidad en la base de datos
        Product saved = productRepository.save(productillo);

        //Mapeo a DTO usando MapStruct
        ProductReadDTO productilloGuardadoDTO = productMapper.toDto(saved);

        return productilloGuardadoDTO;
    }

    @Override
    public List<ProductReadDTO> todosProductos() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(productMapper::toDto)
                .toList();
    }

    @Override
    public ProductReadDTO unProductoEspecifico(Long id) {
        Product product = productRepository.findById(Objects.requireNonNull(id, "id es obligatorio"))
                .orElseThrow(() -> new ResourceNotFoundException("Producto con id " + id + " no encontrado"));
        return productMapper.toDto(product);
    }

    @Override
    @SuppressWarnings("null")
    public ProductNewUpdateDTO actualizarProducto(Long id, ProductNewUpdateDTO productoActualizado) {
        Objects.requireNonNull(id, "id es obligatorio");
        Objects.requireNonNull(productoActualizado, "productoActualizado es obligatorio");
        Product productToUpdate = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto con id " + id + " no encontrado"));

        if (productoActualizado.getSku() != null && !productoActualizado.getSku().equalsIgnoreCase(productToUpdate.getSku())) {
            productRepository.findBySkuIgnoreCase(productoActualizado.getSku()).ifPresent(p -> {
                throw new DuplicateResourceException("El SKU '" + productoActualizado.getSku() + "' ya está en uso por otro producto.");
            });
        }

        if (productoActualizado.getSubcategoriaIds() == null || productoActualizado.getSubcategoriaIds().isEmpty()) {
            throw new BadRequestException("Un producto debe tener al menos una subcategoría");
        }

        productMapper.updateEntity(productoActualizado, productToUpdate);

        Product updatedProduct = productRepository.save(productToUpdate);

        return productMapper.toUpdateDto(updatedProduct);
    }

    @Override
    public void borrarProducto(Long id) {
        if (!productRepository.existsById(Objects.requireNonNull(id, "id es obligatorio"))) {
            throw new ResourceNotFoundException("Producto con id " + id + " no encontrado");
        }
        productRepository.deleteById(id);
    }

}
