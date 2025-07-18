package com.deliverytech.delivery_api.dto.response.produto;

public record ProdutoMaisVendidoResponse(
    String nome,
    Long quantidadeVendida
) {
}
