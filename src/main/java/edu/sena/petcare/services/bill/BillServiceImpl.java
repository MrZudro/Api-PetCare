package edu.sena.petcare.services.bill;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import edu.sena.petcare.dto.bill.BillReadDTO;
import edu.sena.petcare.mapper.BillMapper;
import edu.sena.petcare.repositories.BillRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {

    private final edu.sena.petcare.repositories.BillRepository billRepository;
    private final edu.sena.petcare.mapper.BillMapper billMapper;
    private final edu.sena.petcare.repositories.ProductRepository productRepository;
    private final edu.sena.petcare.repositories.BillDetailRepository billDetailRepository;
    private final edu.sena.petcare.repositories.TransactionsRepository transactionsRepository;
    private final edu.sena.petcare.repositories.MethodPaymentCustomerRepository methodPaymentCustomerRepository;
    private final edu.sena.petcare.repositories.CustomerRepository customerRepository;

    @Override
    public List<BillReadDTO> getAll() {
        return billRepository.findAll()
                .stream()
                .map(billMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BillReadDTO getById(Long id) {
        return billRepository.findById(id)
                .map(billMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Bill not found with id: " + id));
    }

    @Override
    public List<BillReadDTO> getByCustomerId(Long customerId) {
        return billRepository.findByCustomerIdOrderByCreateDateDesc(customerId)
                .stream()
                .map(billMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public BillReadDTO createOrder(edu.sena.petcare.dto.bill.CheckoutDTO checkoutDTO) {
        // 1. Fetch Customer
        var customer = customerRepository.findById(checkoutDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // 2. Fetch Payment Method (Simulated validation)
        var paymentMethod = methodPaymentCustomerRepository.findById(checkoutDTO.getPaymentMethodId())
                .orElseThrow(() -> new RuntimeException("Payment method not found"));

        // 3. Prepare Bill
        edu.sena.petcare.models.Bill bill = new edu.sena.petcare.models.Bill();
        bill.setResolution("RES-001"); // Dummy
        bill.setPrefix("FAC");
        bill.setConsecutive(System.currentTimeMillis()); // Dummy consecutive
        bill.setCufe("CUFE-" + System.currentTimeMillis());
        bill.setCreateDate(java.time.LocalDateTime.now());
        bill.setShippingAddress(checkoutDTO.getShippingAddress());
        bill.setMetodoCliente(paymentMethod);
        bill.setCustomer(customer);
        bill.setDianState("SENT"); // Simulated

        // 4. Calculate Totals & Prepare Details
        java.math.BigDecimal subtotal = java.math.BigDecimal.ZERO;
        java.util.List<edu.sena.petcare.models.BillDetail> details = new java.util.ArrayList<>();

        for (var item : checkoutDTO.getItems()) {
            var product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found: " + item.getProductId()));

            java.math.BigDecimal quantity = new java.math.BigDecimal(item.getQuantity());
            java.math.BigDecimal lineTotal = product.getPrice().multiply(quantity);

            subtotal = subtotal.add(lineTotal);

            edu.sena.petcare.models.BillDetail detail = new edu.sena.petcare.models.BillDetail();
            detail.setProduct(product);
            detail.setAmount(quantity);
            detail.setUnitPrice(product.getPrice());
            detail.setSubtotalLine(lineTotal);

            // Temporary, will associate with bill after bill save
            details.add(detail);
        }

        java.math.BigDecimal taxes = subtotal.multiply(new java.math.BigDecimal("0.19")); // 19% IVA
        java.math.BigDecimal total = subtotal.add(taxes);

        bill.setSubtotal(subtotal);
        bill.setTaxes(taxes);
        bill.setTotalBill(total);

        // 5. Save Bill
        bill = billRepository.save(bill);

        // 6. Save Details (Linking to Bill)
        java.util.List<edu.sena.petcare.models.BillDetail> savedDetails = new java.util.ArrayList<>();
        for (var detail : details) {
            detail.setBill(bill);
            savedDetails.add(billDetailRepository.save(detail));
        }

        // CRITICAL: Set the saved details in the bill object for the mapper
        bill.setBillDetails(savedDetails);

        // 7. Create & Save Transaction
        edu.sena.petcare.models.Transactions transaction = new edu.sena.petcare.models.Transactions();
        transaction.setBill(bill);
        transaction.setMethodTransaction(paymentMethod);
        transaction.setAmount(total);
        transaction.setMoney("COP");
        transaction.setDateTransaction(java.time.LocalDateTime.now());
        transaction.setStateGateway(1); // Approved
        transaction.setIdTransactionGateway("TX-" + System.currentTimeMillis());
        transaction.setAnswerJsonGateway("{\"status\": \"APPROVED\"}");

        transactionsRepository.save(transaction);

        return billMapper.toDto(bill);
    }
}
