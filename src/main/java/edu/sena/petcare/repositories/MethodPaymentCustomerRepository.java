package edu.sena.petcare.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.sena.petcare.models.MethodPaymentCustomer;

@Repository
public interface MethodPaymentCustomerRepository extends JpaRepository<MethodPaymentCustomer, Long> {

    /**
     * Encuentra todos los métodos de pago de un usuario específico
     */
    List<MethodPaymentCustomer> findByUserId(Long userId);

    /**
     * Desmarca todos los métodos de pago de un usuario como no predeterminados
     */
    @Modifying
    @Query("UPDATE MethodPaymentCustomer m SET m.isDefault = false WHERE m.user.id = :userId")
    void unsetAllDefaultsByUserId(@Param("userId") Long userId);
}
