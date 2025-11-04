package edu.sena.petcare.services.bill;

import java.util.List;

import edu.sena.petcare.dto.bill.BillNewDTO;
import edu.sena.petcare.dto.bill.BillReadDTO;

public interface BillService {
    
    // Guardar una nueva orden/factura.
    BillReadDTO createBill(BillNewDTO billNewDTO);
    
    // Consultar el historial de órdenes de un cliente (se asume que el ID del cliente 
    // se pasará o se obtendrá del contexto de seguridad).
    List<BillReadDTO> getCustomerBillHistory(Long customerId);
    
    // Obtener una orden específica por ID.
    BillReadDTO getBillById(Long id);
}