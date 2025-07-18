package com.deliverytech.delivery_api.service;

import com.deliverytech.delivery_api.dto.request.cliente.ClienteRequest;
import com.deliverytech.delivery_api.dto.response.cliente.ClienteRanking;
import com.deliverytech.delivery_api.dto.response.cliente.ClienteResponse;
import com.deliverytech.delivery_api.model.Cliente;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Optional;

@Cacheable("clientes")
public interface ClienteService {
    ClienteResponse cadastrar(ClienteRequest cliente);
    Optional<Cliente> buscarPorId(Long id);
    List<Cliente> listarAtivos();
    Cliente atualizar(Long id, Cliente clienteAtualizado);
    List<ClienteResponse> buscarPorNome(String nome);
    List<ClienteRanking> rankingClientesPorPedidos();
    void ativarDesativar(Long id);
    void excluir(Long id);
}