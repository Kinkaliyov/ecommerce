package com.ecommerce.dto.product;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {

    @NotBlank(message = "Brand cannot be empty")
    private String brand;

    @NotBlank(message = "Model cannot be empty")
    private String model;

    @Size(max = 1000, message = "Description too long")
    private String description;

    @NotNull(message = "Price cannot be empty")
    @DecimalMin(value = "0.01", message = "Price must be greater than zero")
    private BigDecimal price;

    @NotNull(message = "Amount cannot be empty")
    @Min(value = 0, message = "Amount cannot be negative")
    private Integer amount;

    @DecimalMin(value = "0.0", message = "Rating cannot be negative")
    @DecimalMax(value = "5.0", message = "Rating cannot exceed 5")
    private Double rating;

    @NotNull(message = "Category ID cannot be null")
    private Long categoryId;
}