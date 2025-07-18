package com.deliverytech.delivery_api.service.Imp;

import com.deliverytech.delivery_api.dto.request.cliente.ClienteRequest;
import com.deliverytech.delivery_api.dto.response.cliente.ClienteRanking;
import com.deliverytech.delivery_api.dto.response.cliente.ClienteResponse;
import com.deliverytech.delivery_api.mapper.ClienteMapper;
import com.deliverytech.delivery_api.model.Cliente;
import com.deliverytech.delivery_api.repository.ClienteRepository;
import com.deliverytech.delivery_api.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {


    private final ClienteRepository clienteRepository;


    @Override
    public ClienteResponse cadastrar(ClienteRequest cliente) {
        Cliente novoCliente = ClienteMapper.toEntity(cliente);
        if (clienteRepository.existsByEmail(novoCliente.getEmail())) {
            throw new IllegalArgumentException("E-mail já está em uso: " + novoCliente.getEmail());
        }
        novoCliente.setAtivo(true);
        Cliente salvo = clienteRepository.save(novoCliente);
        return ClienteMapper.toResponse(salvo);
    }

    @Override
    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public List<Cliente> listarAtivos() {
        return clienteRepository.findByAtivoTrue();
    }

    @Override
    public Cliente atualizar(Long id, Cliente atualizado) {
        return clienteRepository.findById(id)
                .map(c -> {
                    c.setNome(atualizado.getNome());
                    c.setEmail(atualizado.getEmail());
                    c.setTelefone(atualizado.getTelefone());
                    c.setEndereco(atualizado.getEndereco());
                    return clienteRepository.save(c);
                }).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    @Override
    public void ativarDesativar(Long id) {
        clienteRepository.findById(id).ifPresent( c  -> {
            c.setAtivo(!c.isAtivo());
            clienteRepository.save(c);
        });
    }

    @Override
    public List<ClienteResponse> buscarPorNome(String nome) {
        return clienteRepository.findByNomeContainingIgnoreCase(nome);
    }
    @Override
    public List<ClienteRanking> rankingClientesPorPedidos() {
        return clienteRepository.rankingClientesPorPedidos();
    }
    @Override
    public void excluir(Long id) {
        clienteRepository.deleteById(id);
    }
}