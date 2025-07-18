package com.deliverytech.delivery_api.service.Imp;

import com.deliverytech.delivery_api.dto.request.restaurante.RestauranteRequest;
import com.deliverytech.delivery_api.dto.response.restaurante.RestauranteResponse;
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
    public List<RestauranteResponse> listarTodos() {
        return restauranteRepository.findAll().stream()
                .map(RestauranteMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RestauranteResponse> buscarPorId(Long id) {
        Restaurante restaurante = restauranteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Restaurante não encontrado com ID: " + id));
        return Optional.of(RestauranteMapper.toResponse(restaurante));
    }
    @Override
    public List<RestauranteResponse> buscarPorCategoria(String categoria) {
        return restauranteRepository.findByCategoria(categoria).stream()
                .map(RestauranteMapper::toResponse)
                .collect(Collectors.toList());

    }

    @Override
    public List<RestauranteResponse> buscarPorNome(String nome) {
        return restauranteRepository.findByNomeContainingIgnoreCase(nome).stream()
                .map(RestauranteMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RestauranteResponse atualizar(Long id, RestauranteRequest request) {
        Restaurante restaurante = restauranteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Restaurante não encontrado com ID: " + id));

        restaurante.setNome(request.nome());
        restaurante.setTelefone(request.telefone());
        restaurante.setCategoria(request.categoria());
        restaurante.setTaxaEntrega(request.taxaEntrega());
        restaurante.setEndereco(request.endereco());
        restaurante.setTempoEntregaMinutos(request.tempoEntregaMinutos());

        Restaurante atualizado = restauranteRepository.save(restaurante);
        return RestauranteMapper.toResponse(atualizado);
    }

    @Override
    public List<RestauranteResponse> listarAtivos() {
        return restauranteRepository.findByAtivoTrue().stream()
                .map(RestauranteMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<RestauranteResponse> buscarPorAvaliacaoMinima(BigDecimal avaliacaoMinima) {
        return restauranteRepository.findByAvaliacaoGreaterThanEqual(avaliacaoMinima).stream()
                .map(RestauranteMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<RestauranteResponse> buscarPorTaxaEntregaMaxima(BigDecimal taxaEntregaMaxima) {
        return restauranteRepository.findByTaxaEntregaLessThanEqual(taxaEntregaMaxima).stream()
                .map(RestauranteMapper::toResponse)
                .collect(Collectors.toList());
    }

}
