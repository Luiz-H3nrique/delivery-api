package com.deliverytech.delivery_api.dto.request.restaurante;

import com.deliverytech.delivery_api.model.Endereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record RestauranteRequest(

        @NotBlank(message = "O categoria é obrigatório.")
        String nome,

        @NotBlank(message = "A categoria é obrigatória.")
        String categoria,

        @NotBlank(message = "O telefone é obrigatório.")
        String telefone,

        @Valid
        Endereco endereco,

        @NotNull(message = "A taxa de entrega é obrigatória.")
        @DecimalMin(value = "0.0", inclusive = false, message = "A taxa de entrega deve ser maior que zero.")
        BigDecimal taxaEntrega,

        @NotNull(message = "O tempo de entrega é obrigatório.")
        @Min(value = 10, message = "O tempo de entrega deve ser no mínimo 10 minutos.")
        @Max(value = 120, message = "O tempo de entrega deve ser no máximo 120 minutos.")
        Integer tempoEntregaMinutos

){}