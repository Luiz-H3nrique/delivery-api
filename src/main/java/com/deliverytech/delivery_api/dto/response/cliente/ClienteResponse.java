package com.deliverytech.delivery_api.dto.response.cliente;
import com.deliverytech.delivery_api.model.Endereco;
import jakarta.validation.constraints.NotBlank;

public record ClienteResponse(
        Long id,
        String nome,
        String email,
        String telefone,
        Endereco endereco,
        boolean ativo

) {}