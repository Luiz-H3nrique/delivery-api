package com.deliverytech.delivery_api.dto.request.cliente;

import com.deliverytech.delivery_api.model.Endereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record ClienteRequest(

        @NotBlank(message = "O nome é obrigatório.")
        @Min(value = 3, message = "O nome deve ter pelo menos 3 caracteres.")
        String nome,

        @Email
        @NotBlank(message = "O email é obrigatório.")
        String email,

        @NotBlank(message = "O telefone é obrigatório.")
        String telefone,

        @Valid
        Endereco endereco
) {
}
