package com.deliverytech.delivery_api.service;

import com.deliverytech.delivery_api.dto.request.ProdutoRequest;
import com.deliverytech.delivery_api.dto.response.ProdutoResponse;
import com.deliverytech.delivery_api.dto.response.produto.ProdutoMaisVendidoResponse;

import java.util.List;
import java.util.Optional;

public interface ProdutoService {

    ProdutoResponse cadastrar(ProdutoRequest produtoRequest);

    Optional<ProdutoResponse> buscarPorId(Long id);

    List<ProdutoResponse> buscarPorRestaurante(Long restauranteId);
    ProdutoResponse atualizar(Long id, ProdutoRequest produtoAtualizado);

    void alterarDisponibilidade(Long id, boolean disponivel);
    List<ProdutoResponse> buscarDisponiveis();
    List<ProdutoResponse> buscarPorCategoria(String categoria);
    List<ProdutoMaisVendidoResponse> buscarMaisVendidos();
}