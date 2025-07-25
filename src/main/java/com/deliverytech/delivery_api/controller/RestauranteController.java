package com.deliverytech.delivery_api.controller;

import com.deliverytech.delivery_api.dto.request.restaurante.*;
import com.deliverytech.delivery_api.dto.response.restaurante.RestauranteResponse;
import com.deliverytech.delivery_api.service.RestauranteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Restaurantes", description = "Operações relacionadas a restaurantes cadastrados na plataforma")
@RestController
@RequestMapping("/api/restaurantes")
public class RestauranteController {

    private final RestauranteService restauranteService;

    public RestauranteController(RestauranteService restauranteService) {
        this.restauranteService = restauranteService;
    }

    @Operation(
            summary = "Cadastrar restaurante",
            description = "Cria um novo restaurante com os dados fornecidos"
    )
    @ApiResponse(responseCode = "201", description = "Restaurante criado com sucesso")
    @PostMapping
    public ResponseEntity<RestauranteResponse> cadastrar(
            @RequestBody @Valid RestauranteRequest restauranteRequest
    ) {
        RestauranteResponse response = restauranteService.cadastrar(restauranteRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
            summary = "Buscar restaurantes por categoria",
            description = "Retorna uma lista de restaurantes que pertencem à categoria informada"
    )
    @ApiResponse(responseCode = "200", description = "Restaurantes encontrados")
    @ApiResponse(responseCode = "404", description = "Nenhum restaurante encontrado")
    @GetMapping("/categoria/buscar")
    public ResponseEntity<List<RestauranteResponse>> buscarPorCategoria(
            @RequestBody @Valid RestauranteCategoriaRequest restauranteCategoriaRequest
    ) {
        List<RestauranteResponse> restaurantes = restauranteService.buscarPorCategoria(restauranteCategoriaRequest.categoria());
        return restaurantes.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(restaurantes);
    }

    @Operation(
            summary = "Buscar restaurantes por avaliação mínima",
            description = "Lista restaurantes com avaliação igual ou superior ao valor informado"
    )
    @GetMapping("/avaliacao/buscar")
    public ResponseEntity<List<RestauranteResponse>> buscarPorAvaliacaoMinima(
            @RequestBody @Valid RestauranteAvaliacaoRequest restauranteAvaliacaoRequest
    ) {
        List<RestauranteResponse> restaurantes = restauranteService.buscarPorAvaliacaoMinima(restauranteAvaliacaoRequest.avaliacaoMinima());
        return restaurantes.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(restaurantes);
    }

    @Operation(
            summary = "Buscar restaurantes por taxa de entrega máxima",
            description = "Lista restaurantes com taxa de entrega inferior ou igual à informada"
    )
    @GetMapping("/taxaEntrega/buscar")
    public ResponseEntity<List<RestauranteResponse>> buscarPorTaxaEntregaMaxima(
            @RequestBody @Valid RestauranteTaxaEntregaRequest restauranteTaxaEntregaRequest
    ) {
        List<RestauranteResponse> restaurantes = restauranteService.buscarPorTaxaEntregaMaxima(restauranteTaxaEntregaRequest.taxaEntregaMaxima());
        return restaurantes.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(restaurantes);
    }

    @Operation(
            summary = "Listar restaurantes ativos",
            description = "Retorna todos os restaurantes que estão ativos"
    )
    @ApiResponse(responseCode = "200", description = "Restaurantes retornados com sucesso")
    @GetMapping("/ativos")
    public ResponseEntity<List<RestauranteResponse>> listarAtivos() {
        List<RestauranteResponse> restaurantes = restauranteService.listarAtivos();
        return restaurantes.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(restaurantes);
    }

    @Operation(
            summary = "Buscar restaurantes por nome",
            description = "Retorna restaurantes com nome que contenha o valor informado"
    )
    @GetMapping("/nome/buscar")
    public ResponseEntity<List<RestauranteResponse>> buscarPorNome(
            @RequestBody @Valid RestauranteNomeRequest restauranteNomeRequest
    ) {
        List<RestauranteResponse> restaurantes = restauranteService.buscarPorNome(restauranteNomeRequest.nome());
        return restaurantes.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(restaurantes);
    }

    @Operation(
            summary = "Listar todos os restaurantes",
            description = "Retorna todos os restaurantes cadastrados, ativos ou não"
    )
    @GetMapping("/todos")
    public ResponseEntity<List<RestauranteResponse>> listarTodos() {
        List<RestauranteResponse> restaurantes = restauranteService.listarTodos();
        return restaurantes.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(restaurantes);
    }

    @Operation(
            summary = "Atualizar restaurante",
            description = "Atualiza os dados de um restaurante existente"
    )
    @PutMapping("/{id}")
    public ResponseEntity<RestauranteResponse> atualizar(
            @Parameter(description = "ID do restaurante") @PathVariable Long id,
            @RequestBody @Valid RestauranteRequest restauranteRequest
    ) {
        RestauranteResponse response = restauranteService.atualizar(id, restauranteRequest);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Desativar restaurante",
            description = "Desativa um restaurante a partir do ID informado"
    )
    @ApiResponse(responseCode = "204", description = "Restaurante desativado com sucesso")
    @DeleteMapping("/desativar/{id}")
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID do restaurante") @PathVariable Long id
    ) {
        restauranteService.DesativarRestaurante(id);
        return ResponseEntity.noContent().build();
    }
}
