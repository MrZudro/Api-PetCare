package edu.sena.petcare.dto.bill;

import java.util.List;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillNewDTO {

    // Asumimos que el id del cliente/usuario se obtiene del contexto de seguridad
    // o se inyecta desde otro servicio. Aquí incluimos lo necesario para el cuerpo de la factura.

    @NotNull(message = "El ID de la clínica veterinaria es obligatorio")
    private Long veterinaryClinicId;

    @NotNull(message = "El ID del método de pago es obligatorio")
    private Long methodPaymentCustomerId;
    
    // El empleado se asume del contexto si es un empleado creando la factura
    // o se puede omitir/asignar por defecto si es una compra de cliente final.

    @Valid
    @NotEmpty(message = "La orden debe tener al menos un detalle de producto")
    private List<BillDetailNewDTO> details;

    // Los campos resolution, prefix, consecutive, cufe, dates, subtotal, taxes, totalBill, dianState
    // generalmente se calculan y/o se asignan en el servicio, a excepción de casos muy específicos.
}