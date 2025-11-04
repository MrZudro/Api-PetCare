package edu.sena.petcare.controller;

import edu.sena.petcare.dto.bill.BillNewDTO;
import edu.sena.petcare.dto.bill.BillReadDTO;
import edu.sena.petcare.services.bill.BillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bills") // Usamos "/bills" para facturas/órdenes
@RequiredArgsConstructor
public class BillController {

    private final BillService billService;

    /**
     * POST /bills
     * Crea una nueva orden/factura virtual.
     */
    @PostMapping
    public ResponseEntity<BillReadDTO> createBill(@Valid @RequestBody BillNewDTO billNewDTO) {
        // En una aplicación real, aquí se verificaría la autorización del cliente/empleado.
        BillReadDTO createdBill = billService.createBill(billNewDTO);
        return new ResponseEntity<>(createdBill, HttpStatus.CREATED);
    }

    /**
     * GET /bills/history/{customerId}
     * Consulta el historial de órdenes para un cliente específico.
     * En un entorno real, el 'customerId' se obtendría del contexto de seguridad.
     */
    @GetMapping("/history/{customerId}")
    public ResponseEntity<List<BillReadDTO>> getCustomerHistory(@PathVariable Long customerId) {
        // Solo un cliente autenticado debería poder ver su propio historial.
        List<BillReadDTO> history = billService.getCustomerBillHistory(customerId);
        return ResponseEntity.ok(history);
    }
    
    /**
     * GET /bills/{id}
     * Obtiene una orden/factura específica por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<BillReadDTO> getBillById(@PathVariable Long id) {
        BillReadDTO bill = billService.getBillById(id);
        return ResponseEntity.ok(bill);
    }
}