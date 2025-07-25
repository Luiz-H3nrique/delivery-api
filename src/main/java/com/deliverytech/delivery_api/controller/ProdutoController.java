package com.deliverytech.delivery_api.controller;

import com.deliverytech.delivery_api.dto.request.ProdutoRequest;
import com.deliverytech.delivery_api.dto.response.ProdutoResponse;
import com.deliverytech.delivery_api.dto.response.produto.ProdutoMaisVendidoResponse;
import com.deliverytech.delivery_api.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Produtos", description = "Operações relacionadas aos produtos oferecidos pelos restaurantes")
@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @Operation(
            summary = "Cadastrar novo produto",
            description = "Cria um novo produto com base nas informações fornecidas"
    )
    @ApiResponse(responseCode = "200", description = "Produto criado com sucesso")
    @PostMapping
    public ResponseEntity<ProdutoResponse> criar(
            @RequestBody @Valid ProdutoRequest produtoRequest
    ) {
        ProdutoResponse criado = produtoService.cadastrar(produtoRequest);
        return ResponseEntity.ok(criado);
    }

    @Operation(
            summary = "Buscar produto por ID",
            description = "Retorna as informações de um produto específico"
    )
    @ApiResponse(responseCode = "200", description = "Produto encontrado")
    @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> buscarPorId(
            @Parameter(description = "ID do produto") @PathVariable Long id
    ) {
        return produtoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Listar produtos por restaurante",
            description = "Retorna todos os produtos cadastrados por um restaurante específico"
    )
    @GetMapping("/restaurante/{restauranteId}")
    public ResponseEntity<List<ProdutoResponse>> buscarPorRestaurante(
            @Parameter(description = "ID do restaurante") @PathVariable Long restauranteId
    ) {
        List<ProdutoResponse> produtos = produtoService.buscarPorRestaurante(restauranteId);
        return ResponseEntity.ok(produtos);
    }

    @Operation(
            summary = "Buscar produtos disponíveis",
            description = "Lista todos os produtos que estão com disponibilidade ativa"
    )
    @GetMapping("/disponiveis")
    public ResponseEntity<List<ProdutoResponse>> buscarDisponiveis() {
        List<ProdutoResponse> disponiveis = produtoService.buscarDisponiveis();
        return ResponseEntity.ok(disponiveis);
    }

    @Operation(
            summary = "Buscar produtos por categoria",
            description = "Retorna os produtos de uma determinada categoria"
    )
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<ProdutoResponse>> buscarPorCategoria(
            @Parameter(description = "Categoria do produto") @PathVariable String categoria
    ) {
        List<ProdutoResponse> produtos = produtoService.buscarPorCategoria(categoria);
        return ResponseEntity.ok(produtos);
    }

    @Operation(
            summary = "Buscar produtos mais vendidos",
            description = "Retorna uma lista com os produtos mais vendidos no sistema"
    )
    @GetMapping("/mais-vendidos")
    public ResponseEntity<List<ProdutoMaisVendidoResponse>> buscarMaisVendidos() {
        List<ProdutoMaisVendidoResponse> maisVendidos = produtoService.buscarMaisVendidos();
        return ResponseEntity.ok(maisVendidos);
    }

    @Operation(
            summary = "Atualizar produto",
            description = "Atualiza as informações de um produto existente"
    )
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> atualizar(
            @Parameter(description = "ID do produto") @PathVariable Long id,
            @RequestBody @Valid ProdutoRequest produtoAtualizado
    ) {
        ProdutoResponse atualizado = produtoService.atualizar(id, produtoAtualizado);
        return ResponseEntity.ok(atualizado);
    }

    @Operation(
            summary = "Alterar disponibilidade de produto",
            description = "Ativa ou desativa a disponibilidade de um produto"
    )
    @ApiResponse(responseCode = "204", description = "Disponibilidade alterada com sucesso")
    @PatchMapping("/{id}/disponibilidade")
    public ResponseEntity<Void> alterarDisponibilidade(
            @Parameter(description = "ID do produto") @PathVariable Long id,
            @Parameter(description = "Novo estado de disponibilidade") @RequestParam boolean disponivel
    ) {
        produtoService.alterarDisponibilidade(id, disponivel);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Deletar produto",
            description = "Remove um produto do sistema"
    )
    @ApiResponse(responseCode = "204", description = "Produto deletado com sucesso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID do produto") @PathVariable Long id
    ) {
        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
