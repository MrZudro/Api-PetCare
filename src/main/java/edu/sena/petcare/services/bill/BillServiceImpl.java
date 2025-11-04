package edu.sena.petcare.services.bill;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.sena.petcare.dto.bill.BillNewDTO;
import edu.sena.petcare.dto.bill.BillDetailNewDTO;
import edu.sena.petcare.dto.bill.BillReadDTO;
import edu.sena.petcare.exceptions.ResourceNotFoundException;
import edu.sena.petcare.mapper.BillMapper;
import edu.sena.petcare.models.Bill;
import edu.sena.petcare.models.BillDetail;
import edu.sena.petcare.models.Product;
import edu.sena.petcare.repositories.BillRepository;
import edu.sena.petcare.repositories.ProductRepository;
// Debes importar otros repositorios/servicios:
// import edu.sena.petcare.repositories.VeterinaryClinicRepository; 
// import edu.sena.petcare.repositories.MethodPaymentCustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;
    private final ProductRepository productRepository; // Necesario para obtener precios
    // private final VeterinaryClinicRepository clinicRepository;
    // private final MethodPaymentCustomerRepository methodRepository;
    private final BillMapper billMapper;

    @Override
    @Transactional
    public BillReadDTO createBill(BillNewDTO billNewDTO) {
        // 1. Mapear DTO a Entidad principal (Bill)
        Bill bill = billMapper.toEntity(billNewDTO);

        // 2. Asignar relaciones ManyToOne (Clínica, Método de Pago, Empleado)
        // bill.setVeterinaryClinic(clinicRepository.findById(billNewDTO.getVeterinaryClinicId())
        //     .orElseThrow(() -> new ResourceNotFoundException("Clínica no encontrada")));
        // bill.setMetodoCliente(methodRepository.findById(billNewDTO.getMethodPaymentCustomerId())
        //     .orElseThrow(() -> new ResourceNotFoundException("Método de pago no encontrado")));
        // Asignación de empleado (si aplica)

        // 3. Inicializar campos DIAN/fechas/totales
        bill.setCreateDate(LocalDateTime.now());
        // Lógica para asignar resolution, prefix, consecutive, cufe, dianState (simulación DIAN)
        bill.setResolution("RES-DIAN-XYZ");
        bill.setPrefix("FA");
        bill.setConsecutive(1L); // Obtener el siguiente consecutivo
        bill.setDianState("PENDIENTE");

        BigDecimal totalBill = BigDecimal.ZERO;
        BigDecimal totalTaxes = BigDecimal.ZERO;

        // 4. Procesar y crear BillDetails (y BillTaxes)
        List<BillDetail> details = billNewDTO.getDetails().stream().map(detailDTO -> {
            Product product = productRepository.findById(detailDTO.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado: " + detailDTO.getProductId()));
            
            BillDetail detail = new BillDetail();
            detail.setProduct(product);
            detail.setBill(bill);
            detail.setAmount(detailDTO.getAmount());
            detail.setUnitPrice(product.getPrice()); // Obtener precio del producto

            // Calcular Subtotal de Línea
            BigDecimal subtotalLine = product.getPrice().multiply(detailDTO.getAmount());
            detail.setSubtotalLine(subtotalLine);
            
            // Lógica para BillTaxes: Buscar impuestos del producto (si aplica) y calcular taxValue
            // Esto es más complejo y se omitirá aquí por simplicidad, pero se guardaría con:
            // List<BillTaxes> taxes = ... // Calcular impuestos
            // detail.setTaxes(taxes);
            
            totalBill = totalBill.add(subtotalLine); // Sumar subtotal (sin impuestos)

            return detail;
        }).collect(Collectors.toList());

        // 5. Asignar detalles y totales a la Bill
        bill.setSubtotal(totalBill);
        bill.setTaxes(totalTaxes); // Suma de todos los taxValue de BillTaxes
        bill.setTotalBill(bill.getSubtotal().add(bill.getTaxes())); // Total real

        bill.setBillDetails(details);
        // bill.setBillTaxes(taxDetailsGlobal); // Impuestos de la factura general

        // 6. Guardar la factura y sus detalles
        Bill savedBill = billRepository.save(bill); // Se asume guardado en cascada o se guardan los detalles por separado.

        // 7. Mapear a DTO de Lectura y devolver
        return billMapper.toReadDto(savedBill);
    }

    @Override
    public List<BillReadDTO> getCustomerBillHistory(Long customerId) {
        // Implementación real: Buscar en el repositorio por el ID del cliente.
        // List<Bill> bills = billRepository.findByMetodoCliente_CustomerId(customerId); 
        
        // Simulación:
        List<Bill> bills = billRepository.findAll(); // Reemplazar con la búsqueda real del historial
        
        return billMapper.toReadDtoList(bills);
    }
    
    @Override
    public BillReadDTO getBillById(Long id) {
        Bill bill = billRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Orden/Factura con id " + id + " no encontrada"));
        return billMapper.toReadDto(bill);
    }
    
    // Otros métodos...
}
