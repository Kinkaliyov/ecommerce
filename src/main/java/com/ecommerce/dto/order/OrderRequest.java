package com.ecommerce.dto.order;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "Surname cannot be empty")
    private String surname;

    @NotBlank(message = "Email cannot be empty")
    @Email
    private String email;

    @NotBlank(message = "Phone cannot be empty")
    private String phone;

    @NotBlank(message = "Country cannot be empty")
    private String country;

    @NotBlank(message = "City cannot be empty")
    private String city;

    @NotBlank(message = "Address cannot be empty")
    private String address;

    @NotBlank(message = "Zip code cannot be empty")
    private String zipCode;
}
