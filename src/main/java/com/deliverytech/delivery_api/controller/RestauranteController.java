package com.deliverytech.delivery_api.controller;

import com.deliverytech.delivery_api.dto.request.restaurante.*;
import com.deliverytech.delivery_api.dto.response.restaurante.RestauranteResponse;
import com.deliverytech.delivery_api.service.RestauranteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurantes")
public class RestauranteController {
    private final RestauranteService restauranteService;

    public RestauranteController(RestauranteService restauranteService) {
        this.restauranteService = restauranteService;

    }

    @PostMapping
    public ResponseEntity<RestauranteResponse> cadastrar(@RequestBody @Valid RestauranteRequest restauranteRequest) {
        RestauranteResponse response = restauranteService.cadastrar(restauranteRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }



    @GetMapping("/categoria/buscar")
    public ResponseEntity<List<RestauranteResponse>> buscarPorCategoria(@RequestBody @Valid RestauranteCategoriaRequest restauranteCategoriaRequest) {
        List<RestauranteResponse> restaurantes = restauranteService.buscarPorCategoria(restauranteCategoriaRequest.categoria());
        return restaurantes.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(restaurantes);
    }

    @GetMapping("avaliacao/buscar")
    public ResponseEntity<List<RestauranteResponse>> buscarPorAvaliacaoMinima(@RequestBody @Valid RestauranteAvaliacaoRequest restauranteAvaliacaoRequest) {
        List<RestauranteResponse> restaurantes = restauranteService.buscarPorAvaliacaoMinima(restauranteAvaliacaoRequest.avaliacaoMinima());
        return restaurantes.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(restaurantes);
    }

    @GetMapping("taxaEntrega/buscar")
    public ResponseEntity<List<RestauranteResponse>> buscarPorTaxaEntregaMaxima(@RequestBody @Valid RestauranteTaxaEntregaRequest restauranteTaxaEntregaRequest) {
        List<RestauranteResponse> restaurantes = restauranteService.buscarPorTaxaEntregaMaxima(restauranteTaxaEntregaRequest.taxaEntregaMaxima());
        return restaurantes.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(restaurantes);
    }


    @GetMapping("ativos")
    public ResponseEntity<List<RestauranteResponse>> listarAtivos() {
        List<RestauranteResponse> restaurantes = restauranteService.listarAtivos();
        return restaurantes.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(restaurantes);
    }

    @GetMapping("/nome/buscar")
    public ResponseEntity<List<RestauranteResponse>> buscarPorNome(@RequestBody @Valid RestauranteNomeRequest restauranteNomeRequest) {
        List<RestauranteResponse> restaurantes = restauranteService.buscarPorNome(restauranteNomeRequest.nome());
        return restaurantes.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(restaurantes);
    }



    @GetMapping("/todos")
    public ResponseEntity<List<RestauranteResponse>> listarTodos() {
        List<RestauranteResponse> restaurantes = restauranteService.listarTodos();
        return restaurantes.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(restaurantes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestauranteResponse> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid RestauranteRequest restauranteRequest) {

        RestauranteResponse response = restauranteService.atualizar(id, restauranteRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/desativar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        restauranteService.DesativarRestaurante(id);
        return ResponseEntity.noContent().build();
    }
}

