package edu.sena.petcare.services.product;

import java.util.List;

import edu.sena.petcare.dto.product.ProductNewUpdateDTO;
import edu.sena.petcare.dto.product.ProductReadDTO;

public interface ProductService {

    //Espera un DTO de creacion, el metodo nuevo producto recibe un DTO de creacion o actualizacion que deja como resultado un nuevoProductoDTO(hijo)
    ProductReadDTO nuevoProdcuto(ProductNewUpdateDTO nuevoProductoDTO);
    //Espera una lista donde cada item esta en DTO de lectura de Producto, el metodo que los obtiene se llama todosProductos()
    List<ProductReadDTO> todosProductos();
    //Espera un DTO de producto de lectura, el metodo busca por un Id de tipo Long para devolverlo
    ProductReadDTO unProductoEspecifico(Long id);
    //Espera un DTO de actualizacion de producto, el metodo actualizarProducto recibe por parametro el id del producto a actualizar y el objeto de tipo DTO de actualizacion creado para sustituir los datos.
    ProductNewUpdateDTO actualizarProducto(Long id, ProductNewUpdateDTO productoActualizado);
    //Es un metodo que no devuelve nada, el metodo se encarga de buscar el producto por Id y borrarlo, esto realmente deberia desactivarlo
    void borrarProducto(Long id);
    
}
