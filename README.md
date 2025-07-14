# delivery-api
Projeto desenvolvido durante o curso Arquitetura de Sistemas da Qualifica SP

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

API para gerenciar clientes com operações CRUD e controle de status ativo/inativo.

| Método  | Endpoint               | Descrição                         |
|---------|------------------------|---------------------------------|
| POST    | `/clientes`            | Cadastrar novo cliente           |
| GET     | `/clientes`            | Listar clientes ativos           |
| GET     | `/clientes/{id}`       | Buscar cliente por ID            |
| PUT     | `/clientes/{id}`       | Atualizar cliente                |
| PATCH   | `/clientes/{id}/ativo` | Alternar status ativo/inativo   |
| DELETE  | `/clientes/{id}`       | Deletar cliente                 |

---

### 📥 Criar Cliente

**POST** `/clientes`

**Request Body** (JSON):

```json
{
  "nome": "João da Silva",
  "email": "joao@email.com",
  "telefone": "(11) 99999-9999",
  "endereco": "Rua das Flores, 123",
}

```

**Response (200 ok)**:
```json
{
  "id": 1,
  "nome": "João da Silva",
  "email": "joao@email.com",
  "id": 1,
  "nome": "João da Silva",
  "email": "joao@email.com",
  "telefone": "(11) 99999-9999",
  "endereco": "Rua das Flores, 123",
  "ativo": true
}
```


