package edu.sena.petcare.services.methodpaymentcustomer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.sena.petcare.dto.methodpaymentcustomer.MethodPaymentCustomerNewUpdateDTO;
import edu.sena.petcare.dto.methodpaymentcustomer.MethodPaymentCustomerReadDTO;
import edu.sena.petcare.mapper.MethodPaymentCustomerMapper;
import edu.sena.petcare.models.MethodPaymentCustomer;
import edu.sena.petcare.models.User;
import edu.sena.petcare.repositories.MethodPaymentCustomerRepository;
import edu.sena.petcare.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MethodPaymentCustomerServiceImpl implements MethodPaymentCustomerService {

    private final MethodPaymentCustomerRepository methodPaymentCustomerRepository;
    private final MethodPaymentCustomerMapper methodPaymentCustomerMapper;
    private final UserRepository userRepository;

    @Override
    public List<MethodPaymentCustomerReadDTO> getAll() {
        return methodPaymentCustomerRepository.findAll()
                .stream()
                .map(methodPaymentCustomerMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MethodPaymentCustomerReadDTO getById(Long id) {
        return methodPaymentCustomerRepository.findById(id)
                .map(methodPaymentCustomerMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Método de pago no encontrado con id: " + id));
    }

    @Override
    public List<MethodPaymentCustomerReadDTO> getByUserId(Long userId) {
        // Verificar que el usuario existe
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("Usuario no encontrado con id: " + userId);
        }

        return methodPaymentCustomerRepository.findByUserId(userId)
                .stream()
                .map(methodPaymentCustomerMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public MethodPaymentCustomerReadDTO create(Long userId, MethodPaymentCustomerNewUpdateDTO dto) {
        // Verificar que el usuario existe
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + userId));

        // Si este método se marca como predeterminado, desmarcar los demás
        if (dto.getIsDefault() != null && dto.getIsDefault()) {
            methodPaymentCustomerRepository.unsetAllDefaultsByUserId(userId);
        }

        // Crear la entidad
        MethodPaymentCustomer method = methodPaymentCustomerMapper.toEntity(dto);
        method.setUser(user);

        // Guardar
        MethodPaymentCustomer savedMethod = methodPaymentCustomerRepository.save(method);

        return methodPaymentCustomerMapper.toDto(savedMethod);
    }

    @Override
    @Transactional
    public void delete(Long id, Long userId) {
        // Buscar el método de pago
        MethodPaymentCustomer method = methodPaymentCustomerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Método de pago no encontrado con id: " + id));

        // Verificar que pertenece al usuario
        if (method.getUser() == null || !method.getUser().getId().equals(userId)) {
            throw new RuntimeException("No tiene permisos para eliminar este método de pago");
        }

        // Eliminar
        methodPaymentCustomerRepository.delete(method);
    }

    @Override
    @Transactional
    public MethodPaymentCustomerReadDTO setAsDefault(Long id, Long userId) {
        // Buscar el método de pago
        MethodPaymentCustomer method = methodPaymentCustomerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Método de pago no encontrado con id: " + id));

        // Verificar que pertenece al usuario
        if (method.getUser() == null || !method.getUser().getId().equals(userId)) {
            throw new RuntimeException("No tiene permisos para modificar este método de pago");
        }

        // Desmarcar todos los métodos del usuario como predeterminados
        methodPaymentCustomerRepository.unsetAllDefaultsByUserId(userId);

        // Marcar este como predeterminado
        method.setIsDefault(true);
        MethodPaymentCustomer updatedMethod = methodPaymentCustomerRepository.save(method);

        return methodPaymentCustomerMapper.toDto(updatedMethod);
    }
}
