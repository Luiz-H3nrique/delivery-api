package com.deliverytech.delivery_api.dto.request.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClienteNomeRequest (
    @NotBlank
    String nome,
    @Email
    @NotBlank(message = "O email é obrigatório.")
    String email,

    @NotBlank(message = "O telefone é obrigatório.")
    String telefone
    ){
}