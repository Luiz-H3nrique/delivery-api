package com.deliverytech.delivery_api.config;

import com.deliverytech.delivery_api.model.*;
import com.deliverytech.delivery_api.repository.ClienteRepository;
import com.deliverytech.delivery_api.repository.PedidoRepository;
import com.deliverytech.delivery_api.repository.ProdutoRepository;
import com.deliverytech.delivery_api.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    public void run(String... args) {
        System.out.println("=== INICIANDO CARGA DE DADOS DE TESTE ===");

        pedidoRepository.deleteAll();
        produtoRepository.deleteAll();
        restauranteRepository.deleteAll();
        clienteRepository.deleteAll();

        List<Cliente> clientes = inserirClientes();
        List<Restaurante> restaurantes = inserirRestaurantes();
        List<Produto> produtos = inserirProdutos(restaurantes);
        inserirPedidos(clientes, restaurantes, produtos);

        System.out.println("=== CARGA DE DADOS CONCLUÍDA ===");
    }

    private List<Cliente> inserirClientes() {
        Cliente c1 = new Cliente();
        c1.setNome("Ana");
        c1.setEmail("ana@email.com");
        c1.setAtivo(true);
        c1.setTelefone("11999999999");
        c1.setEndereco(new Endereco("Rua A", "10", "Centro", "CidadeX", "SP", "12345-111"));

        Cliente c2 = new Cliente();
        c2.setNome("Bruno");
        c2.setEmail("bruno@email.com");
        c2.setAtivo(true);
        c2.setTelefone("11988888888");
        c2.setEndereco(new Endereco("Rua B", "20", "Centro", "CidadeY", "SP", "12345-222"));

        Cliente c3 = new Cliente();
        c3.setNome("Carla");
        c3.setEmail("carla@email.com");
        c3.setAtivo(true);
        c3.setTelefone("11977777777");
        c3.setEndereco(new Endereco("Rua C", "30", "Centro", "CidadeZ", "SP", "12345-333"));

        return clienteRepository.saveAll(Arrays.asList(c1, c2, c3));
    }

    private List<Restaurante> inserirRestaurantes() {
        Restaurante r1 = new Restaurante();
        r1.setNome("Pizza Place");
        r1.setCategoria("Pizzaria");
        r1.setTaxaEntrega(new BigDecimal("5.00"));
        r1.setTempoEntregaMinutos(30);
        r1.setTelefone("11999999999");
        r1.setAvaliacao(new BigDecimal("5.0"));
        r1.setEndereco(new Endereco("Rua A", "10", "Centro", "CidadeX", "SP", "12345-111"));
        r1.setAtivo(true);

        Restaurante r2 = new Restaurante();
        r2.setNome("Sushi House");
        r2.setCategoria("Japonesa");
        r2.setEndereco(new Endereco("Rua D", "15", "Jardim", "CidadeX", "SP", "12345-444"));
        r2.setTaxaEntrega(new BigDecimal("8.00"));
        r2.setAvaliacao(new BigDecimal("4.5"));
        r2.setTelefone("11988888888");
        r2.setTempoEntregaMinutos(45);
        r2.setAtivo(true);

        return restauranteRepository.saveAll(Arrays.asList(r1, r2));
    }

    private List<Produto> inserirProdutos(List<Restaurante> restaurantes) {
        Restaurante r1 = restaurantes.get(0);
        Restaurante r2 = restaurantes.get(1);

        Produto p1 = new Produto();
        p1.setNome("Pizza Margherita");
        p1.setDescricao("Pizza clássica");
        p1.setCategoria("Pizzaria");
        p1.setPreco(new BigDecimal("40.00"));
        p1.setDisponivel(true);
        p1.setRestaurante(r1);

        Produto p2 = new Produto();
        p2.setNome("Pizza Calabresa");
        p2.setDescricao("Pizza apimentada");
        p2.setCategoria("Pizzaria");
        p2.setPreco(new BigDecimal("45.00"));
        p2.setDisponivel(true);
        p2.setRestaurante(r1);

        Produto p3 = new Produto();
        p3.setNome("Sushi Salmão");
        p3.setDescricao("8 unidades");
        p3.setCategoria("Japonesa");
        p3.setPreco(new BigDecimal("30.00"));
        p3.setDisponivel(true);
        p3.setRestaurante(r2);

        Produto p4 = new Produto();
        p4.setNome("Temaki Atum");
        p4.setDescricao("Temaki grande");
        p4.setCategoria("Japonesa");
        p4.setPreco(new BigDecimal("25.00"));
        p4.setDisponivel(true);
        p4.setRestaurante(r2);

        Produto p5 = new Produto();
        p5.setNome("Refrigerante");
        p5.setDescricao("Lata 350ml");
        p5.setCategoria("Bebida");
        p5.setPreco(new BigDecimal("6.00"));
        p5.setDisponivel(true);
        p5.setRestaurante(r1);

        return produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
    }

    private void inserirPedidos(List<Cliente> clientes, List<Restaurante> restaurantes, List<Produto> produtos) {
        // Pedido 1
        Pedido pedido1 = new Pedido();
        pedido1.setCliente(clientes.get(0));
        pedido1.setRestaurante(restaurantes.get(0));
        pedido1.setStatus(StatusPedido.CRIADO);
        pedido1.setEnderecoEntrega(
                new Endereco("Rua B", "20", "Centro", "CidadeY", "SP", "12345-111")
        );

        ItemPedido item1 = ItemPedido.builder()
                .produto(produtos.get(0))
                .quantidade(1)
                .precoUnitario(produtos.get(0).getPreco())
                .build();
        item1.setSubtotal();

        ItemPedido item2 = ItemPedido.builder()
                .produto(produtos.get(1))
                .quantidade(1)
                .precoUnitario(produtos.get(1).getPreco())
                .build();
        item2.setSubtotal();

        pedido1.setItens(Arrays.asList(item1, item2));
        pedido1.calcularValorTotal();

        // Pedido 2
        Pedido pedido2 = new Pedido();
        pedido2.setCliente(clientes.get(1));
        pedido2.setRestaurante(restaurantes.get(1));
        pedido2.setStatus(StatusPedido.CRIADO);
        pedido2.setEnderecoEntrega(
                new Endereco("Rua A", "10", "Centro", "CidadeX", "SP", "12345-111")
        );

        ItemPedido item3 = ItemPedido.builder()
                .produto(produtos.get(2))
                .quantidade(1)
                .precoUnitario(produtos.get(2).getPreco())
                .build();
        item3.setSubtotal();

        ItemPedido item4 = ItemPedido.builder()
                .produto(produtos.get(3))
                .quantidade(1)
                .precoUnitario(produtos.get(3).getPreco())
                .build();
        item4.setSubtotal();

        pedido2.setItens(Arrays.asList(item3, item4));
        pedido2.calcularValorTotal();

        pedidoRepository.saveAll(Arrays.asList(pedido1, pedido2));
    }
}