package edu.sena.petcare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.sena.petcare.models.Bill;

public interface BillRepository extends JpaRepository<Bill, Long> {
    // Método para consultar el historial de órdenes de un cliente/usuario.
    // Asumiendo que 'MethodPaymentCustomer' tiene una relación con el usuario/cliente.
    // Por simplicidad, se puede simular buscando por un campo si existiera en Bill.
    // Si tuvieras un campo 'customer' en Bill: List<Bill> findByCustomerId(Long customerId);
    
    // Por ahora, solo se usa JpaRepository, asumiendo que el filtro de cliente
    // se aplicaría en un nivel superior o se añadiría el campo a Bill.
}