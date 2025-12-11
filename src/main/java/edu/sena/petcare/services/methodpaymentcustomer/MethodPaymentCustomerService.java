package edu.sena.petcare.services.methodpaymentcustomer;

import java.util.List;

import edu.sena.petcare.dto.methodpaymentcustomer.MethodPaymentCustomerNewUpdateDTO;
import edu.sena.petcare.dto.methodpaymentcustomer.MethodPaymentCustomerReadDTO;

public interface MethodPaymentCustomerService {

    /**
     * Obtiene todos los métodos de pago
     */
    List<MethodPaymentCustomerReadDTO> getAll();

    /**
     * Obtiene un método de pago por ID
     */
    MethodPaymentCustomerReadDTO getById(Long id);

    /**
     * Obtiene todos los métodos de pago de un usuario específico
     */
    List<MethodPaymentCustomerReadDTO> getByUserId(Long userId);

    /**
     * Crea un nuevo método de pago para un usuario
     */
    MethodPaymentCustomerReadDTO create(Long userId, MethodPaymentCustomerNewUpdateDTO dto);

    /**
     * Elimina un método de pago (validando que pertenezca al usuario)
     */
    void delete(Long id, Long userId);

    /**
     * Marca un método de pago como predeterminado (desmarca los demás del usuario)
     */
    MethodPaymentCustomerReadDTO setAsDefault(Long id, Long userId);
}
