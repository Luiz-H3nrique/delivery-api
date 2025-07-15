package com.deliverytech.delivery_api.service;

import com.deliverytech.delivery_api.dto.request.RestauranteRequest;
import com.deliverytech.delivery_api.dto.response.RestauranteResponse;
import com.deliverytech.delivery_api.model.Restaurante;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface RestauranteService {

    RestauranteResponse cadastrar(RestauranteRequest restauranteRequest);

    Optional<RestauranteResponse> buscarPorId(Long id);

    List<RestauranteResponse> listarTodos();

    List<RestauranteResponse> buscarPorCategoria(String categoria);

    List<RestauranteResponse> buscarAtivos();

    Optional<RestauranteResponse> buscarPorNome(String nome);

    RestauranteResponse atualizar(Long id, RestauranteRequest restauranteAtualizadoRequest);

    List<RestauranteResponse> listarOrdenadoPorAvaliacao();

    List<RestauranteResponse> buscarTop5PorNomeAsc();

    List<RestauranteResponse> buscarPorTaxaDeEntregaMaxima(BigDecimal taxa);

    boolean deletar(Long id);

    void ativarOuDesativar(Long id);
}