package com.deliverytech.delivery_api.dto.request.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record ClienteNomeRequest (
    @NotBlank
    @Min(value = 3, message = "O nome deve ter pelo menos 3 caractere")
    String nome
    ){
}