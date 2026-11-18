package com.ecommerce.dto.auth;

import com.ecommerce.dto.user.UserResponse;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {

    private String token;
    private UserResponse user;
}