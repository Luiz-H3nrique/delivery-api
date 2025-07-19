package com.deliverytech.delivery_api.controller;

import com.deliverytech.delivery_api.dto.request.ProdutoRequest;
import com.deliverytech.delivery_api.dto.response.ProdutoResponse;
import com.deliverytech.delivery_api.dto.response.produto.ProdutoMaisVendidoResponse;
import com.deliverytech.delivery_api.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> criar(@RequestBody @Valid ProdutoRequest produtoRequest) {
        ProdutoResponse criado = produtoService.cadastrar(produtoRequest);
        return ResponseEntity.ok(criado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> buscarPorId(@PathVariable Long id) {
        return produtoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/restaurante/{restauranteId}")
    public ResponseEntity<List<ProdutoResponse>> buscarPorRestaurante(@PathVariable Long restauranteId) {
        List<ProdutoResponse> produtos = produtoService.buscarPorRestaurante(restauranteId);
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/disponiveis")
    public ResponseEntity<List<ProdutoResponse>> buscarDisponiveis() {
        List<ProdutoResponse> disponiveis = produtoService.buscarDisponiveis();
        return ResponseEntity.ok(disponiveis);
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<ProdutoResponse>> buscarPorCategoria(@PathVariable String categoria) {
        List<ProdutoResponse> produtos = produtoService.buscarPorCategoria(categoria);
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/mais-vendidos")
    public ResponseEntity<List<ProdutoMaisVendidoResponse>> buscarMaisVendidos() {
        List<ProdutoMaisVendidoResponse> maisVendidos = produtoService.buscarMaisVendidos();
        return ResponseEntity.ok(maisVendidos);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> atualizar(@PathVariable Long id, @RequestBody @Valid ProdutoRequest produtoAtualizado) {
        ProdutoResponse atualizado = produtoService.atualizar(id, produtoAtualizado);
        return ResponseEntity.ok(atualizado);
    }


    @PatchMapping("/{id}/disponibilidade")
    public ResponseEntity<Void> alterarDisponibilidade(@PathVariable Long id, @RequestParam boolean disponivel) {
        produtoService.alterarDisponibilidade(id, disponivel);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        produtoService.deletar(id);
        return ResponseEntity.noContent().build();

    }

}
