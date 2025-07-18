# delivery-api
Projeto desenvolvido durante o curso Arquitetura de Sistemas da Qualifica SP

# Índice


- [Delivery Tech API](#delivery-tech-api)
- [🚀 Tecnologias](#-tecnologias)
- [⚡ Recursos Modernos Utilizados](#-recursos-modernos-utilizados)
- [🏃‍♂️ Como executar](#-como-executar)
- [📋 Endpoints Globais](#-endpoints-globais)
- [📂 Endpoints - Cliente](#-endpoints---cliente)
    - [📥 Criar Cliente](#-criar-cliente)
    - [📋 Listar Clientes Ativos](#-listar-clientes-ativos)
    - [🔍 Buscar Cliente por ID](#-buscar-cliente-por-id)
    - [🔍 Buscar Cliente por Nome](#-buscar-cliente-por-nome)
    - [✏️ Atualizar Cliente](#-atualizar-cliente)
    - [🔄 Alternar Status Ativo/Inativo](#-alternar-status-ativo-inativo)
    - [🗑️ Deletar Cliente](#-deletar-cliente)
    - [📊 Ranking de Clientes](#-ranking-de-clientes)
- [📂 Endpoints - Restaurante](#-endpoints---restaurante)
    - [📥 Criar Restaurante](#-criar-restaurante)
    - [📋 Listar Restaurantes Ativos](#-listar-restaurantes-ativos)
    - [📋 Listar Todos os Restaurantes](#-listar-todos-os-restaurantes)
    - [🔍 Buscar por Categoria](#-buscar-por-categoria)
    - [🔍 Buscar por Avaliação Mínima](#-buscar-por-avaliacao-minima)
    - [🔍 Buscar por Taxa de Entrega Máxima](#-buscar-por-taxa-de-entrega-maxima)
    - [🔍 Buscar por Nome](#-buscar-por-nome)
    - [✏️ Atualizar Restaurante](#-atualizar-restaurante)
- [📂 Endpoints - Produto](#-endpoints---produto)
    - [📥 Criar Produto](#-criar-produto)
    - [📋 Buscar Produto por ID](#-buscar-produto-por-id)
    - [📋 Listar Produtos de um Restaurante](#-listar-produtos-de-um-restaurante)
    - [📋 Listar Produtos Disponíveis](#-listar-produtos-disponiveis)
    - [🔍 Buscar Produtos por Categoria](#-buscar-produtos-por-categoria)
    - [📈 Produtos Mais Vendidos](#-produtos-mais-vendidos)
    - [✏️ Atualizar Produto](#-atualizar-produto)
    - [🔄 Alterar Disponibilidade do Produto](#-alterar-disponibilidade-do-produto)
- [📂 Endpoints - Pedido](#-endpoints---pedido)
    - [📥 Criar Pedido](#-criar-pedido)
    - [📋 Buscar Pedido por ID](#-buscar-pedido-por-id)
    - [📋 Listar Pedidos de um Cliente](#-listar-pedidos-de-um-cliente)
    - [📋 Listar Pedidos de um Restaurante](#-listar-pedidos-de-um-restaurante)
    - [✏️ Atualizar Pedido](#-atualizar-pedido)
    - [🔄 Atualizar Status do Pedido](#-atualizar-status-do-pedido)
    - [🗑️ Cancelar Pedido](#-cancelar-pedido)

# Delivery Tech API

Sistema de delivery desenvolvido com Spring Boot e Java 21.

## 🚀 Tecnologias
- **Java 21 LTS**
- Spring Boot 3.5.3
- Spring Web
- Spring Data JPA
- H2 Database
- Maven

## ⚡ Recursos Modernos Utilizados
- Records (Java 14+)
- Text Blocks (Java 15+)
- Pattern Matching (Java 17+)
- Virtual Threads (Java 21)

## 🏃‍♂️ Como executar
1. **Pré-requisitos:** JDK 21 instalado
2. Clone o repositório
3. Execute: `./mvnw spring-boot:run`
4. Acesse: http://localhost:8080/health

## 📋 Endpoints Globais
- GET /health - Status da aplicação (inclui versão Java)
- GET /info - Informações da aplicação
- GET /h2-console - Console do banco H2

---

## 📂 Endpoints - Cliente

API para gerenciar clientes com operações CRUD, busca por nome, ranking e controle de status ativo/inativo.

| Método  | Endpoint                       | Descrição                                 |
|---------|--------------------------------|-------------------------------------------|
| POST    | `/clientes`                    | Cadastrar novo cliente                    |
| GET     | `/clientes`                    | Listar clientes ativos                    |
| GET     | `/clientes/{id}`               | Buscar cliente por ID                     |
| GET     | `/clientes/nome`               | Buscar clientes por nome                  |
| PUT     | `/clientes/{id}`               | Atualizar cliente                         |
| PATCH   | `/clientes/{id}/status`        | Alternar status ativo/inativo             |
| DELETE  | `/clientes/{id}`               | Deletar cliente                           |
| GET     | `/clientes/ranking`            | Ranking de clientes por pedidos           |

---

### 📥 Criar Cliente

**POST** `/clientes`

**Request Body:**
```json
{
  "nome": "João da Silva",
  "email": "joao@email.com",
  "telefone": "(11) 99999-9999",
  "endereco": "Rua das Flores, 123"
}
```
**Exemplo de resposta:**
```json
{
  "id": 1,
  "nome": "João da Silva",
  "email": "joao@email.com",
  "telefone": "(11) 99999-9999",
  "endereco": "Rua das Flores, 123",
  "ativo": true
}
```

### 📋 Listar Clientes Ativos

**GET** `/clientes`

Retorna todos os clientes ativos.

**Exemplo de resposta:**
```json
[
  {
    "id": 1,
    "nome": "João da Silva",
    "email": "joao@email.com",
    "telefone": "(11) 99999-9999",
    "endereco": "Rua das Flores, 123",
    "ativo": true
  },
  {
    "id": 2,
    "nome": "Maria Oliveira",
    "email": "maria@email.com",
    "telefone": "(11) 88888-8888",
    "endereco": "Av. Brasil, 456",
    "ativo": true
  }
]
```

### 🔍 Buscar Cliente por ID

**GET** `/clientes/{id}`

**Exemplo de resposta:**
```json
{
  "id": 1,
  "nome": "João da Silva",
  "email": "joao@email.com",
  "telefone": "(11) 99999-9999",
  "endereco": "Rua das Flores, 123",
  "ativo": true
}
```

### 🔍 Buscar Cliente por Nome

**GET** `/clientes/nome`

**Request Body:**
```json
{
  "nome": "João"
}
```
**Exemplo de resposta:**
```json
[
  {
    "id": 1,
    "nome": "João da Silva",
    "email": "joao@email.com",
    "telefone": "(11) 99999-9999",
    "endereco": "Rua das Flores, 123",
    "ativo": true
  }
]
```

### ✏️ Atualizar Cliente

**PUT** `/clientes/{id}`

**Request Body:**
```json
{
  "nome": "João da Silva Santos",
  "email": "joao.santos@email.com",
  "telefone": "(11) 99999-8888",
  "endereco": "Rua das Flores, 123, Apt 45"
}
```
**Exemplo de resposta:**
```json
{
  "id": 1,
  "nome": "João da Silva Santos",
  "email": "joao.santos@email.com",
  "telefone": "(11) 99999-8888",
  "endereco": "Rua das Flores, 123, Apt 45",
  "ativo": true
}
```

### 🔄 Alternar Status Ativo/Inativo

**PATCH** `/clientes/{id}/status`

Alterna o status ativo/inativo do cliente.

**Exemplo de resposta:**
```json
{
  "id": 1,
  "nome": "João da Silva",
  "ativo": false
}
```

### 🗑️ Deletar Cliente

**DELETE** `/clientes/{id}`

Cliente será deletado permanentemente.

### 📊 Ranking de Clientes

**GET** `/clientes/ranking`

Retorna ranking dos clientes por quantidade de pedidos.

**Exemplo de resposta:**
```json
[
  {
    "id": 1,
    "nome": "João da Silva",
    "totalPedidos": 15
  },
  {
    "id": 2,
    "nome": "Maria Oliveira",
    "totalPedidos": 12
  }
]
```

---

## 📂 Endpoints - Restaurante

API para gerenciar restaurantes, incluindo cadastro, busca, atualização e listagem.

| Método  | Endpoint                                 | Descrição                                 |
|---------|------------------------------------------|-------------------------------------------|
| POST    | `/restaurantes`                          | Cadastrar novo restaurante                |
| GET     | `/restaurantes/ativos`                   | Listar restaurantes ativos                |
| GET     | `/restaurantes/todos`                    | Listar todos os restaurantes              |
| GET     | `/restaurantes/categoria/buscar`         | Buscar restaurantes por categoria         |
| GET     | `/restaurantes/avaliacao/buscar`         | Buscar por avaliação mínima               |
| GET     | `/restaurantes/taxaEntrega/buscar`       | Buscar por taxa de entrega máxima         |
| GET     | `/restaurantes/nome/buscar`              | Buscar restaurantes por nome              |
| PUT     | `/restaurantes/{id}`                     | Atualizar restaurante                     |

---

### 📥 Criar Restaurante

**POST** `/restaurantes`

**Request Body** (JSON):

```json
{
  "nome": "Pizza do Bairro",
  "categoria": "Pizza",
  "endereco": "Av. Central, 100",
  "taxaEntrega": 5.00,
  "avaliacao": 4.5
}
```

### 📋 Listar Restaurantes Ativos

**GET** `/restaurantes/ativos`

Retorna todos os restaurantes ativos.

### 📋 Listar Todos os Restaurantes

**GET** `/restaurantes/todos`

**Exemplo de resposta:**
```json
[
  {
    "id": 1,
    "nome": "Pizza do Bairro",
    "categoria": "Pizza",
    "endereco": "Av. Central, 100",
    "taxaEntrega": 5.00,
    "avaliacao": 4.5,
    "ativo": true
  },
  {
    "id": 2,
    "nome": "Sushi Express",
    "categoria": "Japonesa",
    "endereco": "Rua Japão, 50",
    "taxaEntrega": 8.00,
    "avaliacao": 4.7,
    "ativo": true
  }
]
```

### 🔍 Buscar por Categoria

**GET** `/restaurantes/categoria/buscar`

**Request Body:**
```json
{
  "categoria": "Pizza"
}
```
**Exemplo de resposta:**
```json
[
  {
    "id": 1,
    "nome": "Pizza do Bairro",
    "categoria": "Pizza",
    "endereco": "Av. Central, 100",
    "taxaEntrega": 5.00,
    "avaliacao": 4.5,
    "ativo": true
  }
]
```

### 🔍 Buscar por Avaliação Mínima

**GET** `/restaurantes/avaliacao/buscar?avaliacaoMinima=4.0`

**Exemplo de resposta:**
```json
[
  {
    "id": 2,
    "nome": "Sushi Express",
    "categoria": "Japonesa",
    "endereco": "Rua Japão, 50",
    "taxaEntrega": 8.00,
    "avaliacao": 4.7,
    "ativo": true
  }
]
```

### 🔍 Buscar por Taxa de Entrega Máxima

**GET** `/restaurantes/taxaEntrega/buscar`

**Request Body:**
```json
{
  "taxaEntregaMaxima": 6.00
}
```
**Exemplo de resposta:**
```json
[
  {
    "id": 1,
    "nome": "Pizza do Bairro",
    "categoria": "Pizza",
    "endereco": "Av. Central, 100",
    "taxaEntrega": 5.00,
    "avaliacao": 4.5,
    "ativo": true
  }
]
```

### 🔍 Buscar por Nome

**GET** `/restaurantes/nome/buscar?nome=Express`

**Exemplo de resposta:**
```json
[
  {
    "id": 2,
    "nome": "Sushi Express",
    "categoria": "Japonesa",
    "endereco": "Rua Japão, 50",
    "taxaEntrega": 8.00,
    "avaliacao": 4.7,
    "ativo": true
  }
]
```

### ✏️ Atualizar Restaurante

**PUT** `/restaurantes/{id}`

**Request Body:**
```json
{
  "nome": "Pizza do Centro",
  "categoria": "Pizza",
  "endereco": "Av. Nova, 200",
  "taxaEntrega": 7.00,
  "avaliacao": 4.8
}
```
**Exemplo de resposta:**
```json
{
  "id": 1,
  "nome": "Pizza do Centro",
  "categoria": "Pizza",
  "endereco": "Av. Nova, 200",
  "taxaEntrega": 7.00,
  "avaliacao": 4.8,
  "ativo": true
}
```

---

## 📂 Endpoints - Produto

API para gerenciar produtos dos restaurantes, incluindo cadastro, busca, atualização, disponibilidade e mais vendidos.

| Método  | Endpoint                                         | Descrição                                 |
|---------|--------------------------------------------------|-------------------------------------------|
| POST    | `/produtos`                                      | Cadastrar novo produto                    |
| GET     | `/produtos/{id}`                                 | Buscar produto por ID                     |
| GET     | `/produtos/restaurante/{restauranteId}`          | Listar produtos de um restaurante         |
| GET     | `/produtos/disponiveis`                          | Listar produtos disponíveis               |
| GET     | `/produtos/categoria/{categoria}`                | Buscar produtos por categoria             |
| GET     | `/produtos/mais-vendidos`                        | Listar produtos mais vendidos             |
| PUT     | `/produtos/{id}`                                 | Atualizar produto                         |
| PATCH   | `/produtos/{id}/disponibilidade?disponivel=true` | Alterar disponibilidade do produto        |

---

### 📥 Criar Produto

**POST** `/produtos`

**Request Body:**
```json
{
  "nome": "Pizza Calabresa",
  "descricao": "Pizza de calabresa com queijo",
  "preco": 39.90,
  "categoria": "Pizza",
  "restauranteId": 1,
  "disponivel": true
}
```

### 📋 Buscar Produto por ID

**GET** `/produtos/1`

**Exemplo de resposta:**
```json
{
  "id": 1,
  "nome": "Pizza Calabresa",
  "descricao": "Pizza de calabresa com queijo",
  "preco": 39.90,
  "categoria": "Pizza",
  "restauranteId": 1,
  "disponivel": true
}
```

### 📋 Listar Produtos de um Restaurante

**GET** `/produtos/restaurante/1`

**Exemplo de resposta:**
```json
[
  {
    "id": 1,
    "nome": "Pizza Calabresa",
    "descricao": "Pizza de calabresa com queijo",
    "preco": 39.90,
    "categoria": "Pizza",
    "restauranteId": 1,
    "disponivel": true
  },
  {
    "id": 2,
    "nome": "Pizza Portuguesa",
    "descricao": "Pizza com ovos, presunto e cebola",
    "preco": 42.90,
    "categoria": "Pizza",
    "restauranteId": 1,
    "disponivel": true
  }
]
```

### 📋 Listar Produtos Disponíveis

**GET** `/produtos/disponiveis`

**Exemplo de resposta:**
```json
[
  {
    "id": 1,
    "nome": "Pizza Calabresa",
    "disponivel": true
  },
  {
    "id": 3,
    "nome": "Sushi Salmão",
    "disponivel": true
  }
]
```

### 🔍 Buscar Produtos por Categoria

**GET** `/produtos/categoria/Pizza`

**Exemplo de resposta:**
```json
[
  {
    "id": 1,
    "nome": "Pizza Calabresa",
    "categoria": "Pizza"
  },
  {
    "id": 2,
    "nome": "Pizza Portuguesa",
    "categoria": "Pizza"
  }
]
```

### 📈 Produtos Mais Vendidos

**GET** `/produtos/mais-vendidos`

**Exemplo de resposta:**
```json
[
  {
    "id": 1,
    "nome": "Pizza Calabresa",
    "quantidadeVendida": 120
  },
  {
    "id": 3,
    "nome": "Sushi Salmão",
    "quantidadeVendida": 98
  }
]
```

### ✏️ Atualizar Produto

**PUT** `/produtos/1`

**Request Body:**
```json
{
  "nome": "Pizza Calabresa Especial",
  "descricao": "Pizza de calabresa com queijo extra",
  "preco": 44.90,
  "categoria": "Pizza",
  "restauranteId": 1,
  "disponivel": true
}
```
**Exemplo de resposta:**
```json
{
  "id": 1,
  "nome": "Pizza Calabresa Especial",
  "descricao": "Pizza de calabresa com queijo extra",
  "preco": 44.90,
  "categoria": "Pizza",
  "restauranteId": 1,
  "disponivel": true
}
```

### 🔄 Alterar Disponibilidade do Produto

**PATCH** `/produtos/1/disponibilidade?disponivel=false`

Produto ficará indisponível para pedidos.

---

## 📂 Endpoints - Pedido

API para gerenciar pedidos, incluindo criação, busca, atualização, cancelamento e listagem por cliente/restaurante.

| Método  | Endpoint                                 | Descrição                                 |
|---------|------------------------------------------|-------------------------------------------|
| POST    | `/pedidos`                              | Criar novo pedido                         |
| GET     | `/pedidos/{id}`                         | Buscar pedido por ID                      |
| GET     | `/pedidos/cliente?clienteId=1`          | Listar pedidos de um cliente              |
| GET     | `/pedidos/restaurante?restauranteId=1`  | Listar pedidos de um restaurante          |
| PUT     | `/pedidos/{id}/status?status=ENTREGUE`  | Atualizar status do pedido                |
| PUT     | `/pedidos/{id}`                         | Atualizar pedido                          |
| DELETE  | `/pedidos/{id}`                         | Cancelar pedido                           |

---

### 📥 Criar Pedido

**POST** `/pedidos`

**Request Body:**
```json
{
  "clienteId": 1,
  "restauranteId": 1,
  "produtos": [
    { "produtoId": 1, "quantidade": 2 },
    { "produtoId": 3, "quantidade": 1 }
  ],
  "enderecoEntrega": "Rua das Flores, 123",
  "formaPagamento": "CARTAO"
}
```
**Exemplo de resposta:**
```json
{
  "id": 10,
  "clienteId": 1,
  "restauranteId": 1,
  "produtos": [
    { "produtoId": 1, "nome": "Pizza Calabresa", "quantidade": 2 },
    { "produtoId": 3, "nome": "Sushi Salmão", "quantidade": 1 }
  ],
  "status": "REALIZADO",
  "total": 119.70,
  "enderecoEntrega": "Rua das Flores, 123",
  "formaPagamento": "CARTAO"
}
```

### 📋 Buscar Pedido por ID

**GET** `/pedidos/10`

**Exemplo de resposta:**
```json
{
  "id": 10,
  "clienteId": 1,
  "restauranteId": 1,
  "produtos": [
    { "produtoId": 1, "nome": "Pizza Calabresa", "quantidade": 2 },
    { "produtoId": 3, "nome": "Sushi Salmão", "quantidade": 1 }
  ],
  "status": "REALIZADO",
  "total": 119.70,
  "enderecoEntrega": "Rua das Flores, 123",
  "formaPagamento": "CARTAO"
}
```

### 📋 Listar Pedidos de um Cliente

**GET** `/pedidos/cliente?clienteId=1`

**Exemplo de resposta:**
```json
[
  {
    "id": 10,
    "status": "ENTREGUE",
    "total": 119.70
  },
  {
    "id": 12,
    "status": "CANCELADO",
    "total": 42.90
  }
]
```

### 📋 Listar Pedidos de um Restaurante

**GET** `/pedidos/restaurante?restauranteId=1`

**Exemplo de resposta:**
```json
[
  {
    "id": 10,
    "clienteId": 1,
    "status": "ENTREGUE",
    "total": 119.70
  },
  {
    "id": 13,
    "clienteId": 2,
    "status": "REALIZADO",
    "total": 89.90
  }
]
```

### ✏️ Atualizar Pedido

**PUT** `/pedidos/10`

**Request Body:**
```json
{
  "produtos": [
    { "produtoId": 1, "quantidade": 1 },
    { "produtoId": 2, "quantidade": 2 }
  ],
  "enderecoEntrega": "Rua Nova, 200",
  "formaPagamento": "DINHEIRO"
}
```
**Exemplo de resposta:**
```json
{
  "id": 10,
  "produtos": [
    { "produtoId": 1, "nome": "Pizza Calabresa", "quantidade": 1 },
    { "produtoId": 2, "nome": "Pizza Portuguesa", "quantidade": 2 }
  ],
  "status": "REALIZADO",
  "total": 125.70,
  "enderecoEntrega": "Rua Nova, 200",
  "formaPagamento": "DINHEIRO"
}
```

### 🔄 Atualizar Status do Pedido

**PUT** `/pedidos/10/status?status=ENTREGUE`

**Exemplo de resposta:**
```json
{
  "id": 10,
  "status": "ENTREGUE"
}
```

### 🗑️ Cancelar Pedido

**DELETE** `/pedidos/10`

Pedido será cancelado e não poderá ser entregue.
