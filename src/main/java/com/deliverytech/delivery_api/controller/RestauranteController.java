package com.deliverytech.delivery_api.controller;

import com.deliverytech.delivery_api.dto.request.restaurante.RestauranteCategoriaRequest;
import com.deliverytech.delivery_api.dto.request.restaurante.RestauranteRequest;
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
        List<RestauranteResponse> restaurantes = restauranteService.buscarPorCategoria(restauranteCategoriaRequest
                .categoria());
        return restaurantes.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(restaurantes);
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<RestauranteResponse>> listarAtivos() {
        return ResponseEntity.ok(restauranteService.listarTodos());
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
}

