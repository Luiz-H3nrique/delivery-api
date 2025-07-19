package com.deliverytech.delivery_api.dto.request.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClienteEmailRequest (
        @Email
        @NotBlank
        String email

) {
}
