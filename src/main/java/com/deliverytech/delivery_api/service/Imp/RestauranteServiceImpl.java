package com.deliverytech.delivery_api.service.Imp;

import com.deliverytech.delivery_api.dto.request.restaurante.RestauranteRequest;
import com.deliverytech.delivery_api.dto.response.RestauranteResponse;
import com.deliverytech.delivery_api.mapper.RestauranteMapper;
import com.deliverytech.delivery_api.model.Restaurante;
import com.deliverytech.delivery_api.repository.RestauranteRepository;
import com.deliverytech.delivery_api.service.RestauranteService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestauranteServiceImpl implements RestauranteService {

    private final RestauranteRepository restauranteRepository;

    @Override
    public RestauranteResponse cadastrar(RestauranteRequest restauranteRequest) {
        Restaurante restauranteEntidade = RestauranteMapper.toEntity(restauranteRequest);
        restauranteEntidade.setAtivo(true);
        Restaurante salvo = restauranteRepository.save(restauranteEntidade);
        return RestauranteMapper.toResponse(salvo);
    }

    @Override
    public Optional<RestauranteResponse> buscarPorId(Long id) {
        return restauranteRepository.findById(id)
                .map(RestauranteMapper::toResponse);
    }

    @Override
    public List<RestauranteResponse> listarTodos() {
        return restauranteRepository.findAll().stream()
                .map(RestauranteMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<RestauranteResponse> buscarPorCategoria(String categoria) {
        return restauranteRepository.findByCategoria(categoria).stream()
                .map(RestauranteMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<RestauranteResponse> buscarAtivos() {
        return restauranteRepository.findByAtivoTrue().stream()
                .map(RestauranteMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RestauranteResponse> buscarPorNome(String nome) {
        return restauranteRepository.findByNome(nome)
                .map(RestauranteMapper::toResponse);
    }

    @Override
    public RestauranteResponse atualizar(Long id, RestauranteRequest request) {
        Restaurante restaurante = restauranteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Restaurante não encontrado com ID: " + id));

        restaurante.setNome(request.nome());
        restaurante.setTelefone(request.telefone());
        restaurante.setCategoria(request.categoria());
        restaurante.setTaxaEntrega(request.taxaEntrega());
        restaurante.setTempoEntregaMinutos(request.tempoEntregaMinutos());

        Restaurante atualizado = restauranteRepository.save(restaurante);
        return RestauranteMapper.toResponse(atualizado);
    }


    @Override
    public List<RestauranteResponse> listarOrdenadoPorAvaliacao() {
        return restauranteRepository.findAllByOrderByAvaliacaoDesc().stream()
                .map(RestauranteMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<RestauranteResponse> buscarTop5PorNomeAsc() {
        return restauranteRepository.findTop5ByOrderByNomeAsc().stream()
                .map(RestauranteMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<RestauranteResponse> buscarPorTaxaDeEntregaMaxima(BigDecimal taxa) {
        return restauranteRepository.findByTaxaEntregaLessThanEqual(taxa).stream()
                .map(RestauranteMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deletar(Long id) {
        if (!restauranteRepository.existsById(id)) {
            return false;
        }
        restauranteRepository.deleteById(id);
        return true;
    }

    @Override
    public void ativarOuDesativar(Long id) {
        restauranteRepository.findById(id).ifPresent(restaurante -> {
            restaurante.setAtivo(!restaurante.isAtivo());
            restauranteRepository.save(restaurante);
        });
    }
}