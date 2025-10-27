package edu.sena.petcare.services.product;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.sena.petcare.dto.product.ProductNewUpdateDTO;
import edu.sena.petcare.dto.product.ProductReadDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    @Override
    public ProductNewUpdateDTO nuevoProdcuto(ProductNewUpdateDTO nuevoProductoDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'nuevoProdcuto'");
    }

    @Override
    public List<ProductReadDTO> todosProductos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'todosProductos'");
    }

    @Override
    public ProductReadDTO unProductoEspecifico(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'unProductoEspecifico'");
    }

    @Override
    public ProductNewUpdateDTO actualizarProducto(Long id, ProductNewUpdateDTO productoActualizado) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizarProducto'");
    }

    @Override
    public void borrarProducto(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'borrarProducto'");
    }

}
