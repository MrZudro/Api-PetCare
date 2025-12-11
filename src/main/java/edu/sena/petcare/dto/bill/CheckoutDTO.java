package edu.sena.petcare.dto.bill;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutDTO {
    private Long userId;
    private Long paymentMethodId;
    private String shippingAddress;
    private List<CheckoutItemDTO> items;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CheckoutItemDTO {
        private Long productId;
        private Integer quantity;
    }
}
