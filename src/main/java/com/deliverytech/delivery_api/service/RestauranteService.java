package com.deliverytech.delivery_api.service;

import com.deliverytech.delivery_api.dto.request.restaurante.RestauranteRequest;
import com.deliverytech.delivery_api.dto.response.restaurante.RestauranteResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface RestauranteService {


    RestauranteResponse cadastrar(RestauranteRequest restauranteRequest);

    List<RestauranteResponse> listarTodos();

    List<RestauranteResponse> buscarPorCategoria(String categoria);
    List<RestauranteResponse> listarAtivos();
    List<RestauranteResponse> buscarPorNome(String nome);
    List<RestauranteResponse> buscarPorAvaliacaoMinima(BigDecimal avaliacaoMinima);
    RestauranteResponse atualizar(Long id, RestauranteRequest restauranteAtualizadoRequest);
    Optional<RestauranteResponse> buscarPorId(Long id);
    List<RestauranteResponse> buscarPorTaxaEntregaMaxima(BigDecimal taxaEntregaMaxima);
}