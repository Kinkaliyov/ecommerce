package com.ecommerce.dto.product;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {
    private Long id;
    private String brand;
    private String model;
    private String description;
    private BigDecimal price;
    private Integer amount;
    private String imageUrl;
    private Double rating;

    private Long categoryId;
    private String categoryName;

    private Long sellerId;
    private String sellerUsername;
}