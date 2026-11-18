package com.ecommerce.dto.order;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {
    private Long id;
    private BigDecimal totalPrice;
    private String status;
    private LocalDateTime createdAt;

    private String name;
    private String surname;
    private String email;
    private String phone;

    private String country;
    private String city;
    private String address;
    private String zipCode;

    private List<OrderItemResponse> items;
}
