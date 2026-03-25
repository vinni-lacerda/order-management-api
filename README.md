# Order Management API

API RESTful para gerenciamento de pedidos, produtos e usuários, construída com foco em boas práticas de arquitetura backend, separação de responsabilidades e manutenibilidade.

## 📌 Visão Geral

O sistema permite:

- Gerenciamento completo de usuários, produtos e pedidos
- Associação entre pedidos e itens (order items)
- Controle de status de pedidos
- Tratamento estruturado de erros
- Isolamento entre camadas utilizando DTOs e mapeadores

A aplicação segue uma arquitetura em camadas (layered architecture), promovendo baixo acoplamento e alta coesão entre os componentes.

---

## 🧱 Arquitetura

A estrutura do projeto foi organizada com base no padrão:

Controller → Service → Repository


### Camadas

- **Controller**
  - Responsável pela exposição dos endpoints REST
  - Não contém regra de negócio

- **Service**
  - Contém a lógica de negócio da aplicação
  - Orquestra chamadas entre repositórios e validações

- **Repository**
  - Abstração de acesso a dados via Spring Data JPA

- **DTO (Data Transfer Object)**
  - Evita exposição direta das entidades
  - Controla o formato de entrada e saída da API

- **Mapper**
  - Responsável pela conversão entre Entity ↔ DTO
  - Mantém isolamento entre domínio e camada de transporte

- **Exception Handling**
  - Uso de exceções customizadas por domínio
  - Preparado para integração com `@ControllerAdvice`

---

## 🗂 Estrutura do Projeto

controllers/
services/
repositories/
entities/
dtos/
mappers/
exceptions/
enums/


---

## ⚙️ Tecnologias

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- Maven
- Banco de dados relacional (H2 / PostgreSQL / MySQL)

---

## 🔁 Fluxo de Requisição

1. A requisição HTTP é recebida pelo Controller
2. O Controller delega para a camada de Service
3. A Service executa regras de negócio e validações
4. O Repository realiza a persistência
5. A resposta é convertida para DTO e retornada ao cliente

---

## 📦 Domínio

### User
- Representa os usuários do sistema

### Product
- Entidade de produto com atributos básicos

### Order
- Representa um pedido
- Possui relacionamento com itens e status

### OrderItem
- Representa a associação entre pedido e produto
- Contém quantidade e composição do pedido

### OrderStatus (enum)
- Define os estados possíveis de um pedido

---

## ⚠️ Tratamento de Erros

A aplicação utiliza exceções específicas por domínio:

- `ResourceNotFoundException`
- `UserNotFoundException`
- `ProductNotFoundException`
- `OrderNotFoundException`
- `OrderItemNotFoundException`

Facilitando:
- Clareza semântica
- Tratamento centralizado
- Padronização de respostas de erro

---

## ▶️ Como Executar

### Pré-requisitos

- Java 17+
- Maven

### Execução

```bash
git clone https://github.com/seu-usuario/order-management-api.git
cd order-management-api
mvn spring-boot:run
```

A aplicação estará disponível em:
```
http://localhost:8080
```
📬 Endpoints (Resumo)

Users
```
GET    /users
GET    /users/{id}
POST   /users
PUT    /users/{id}
DELETE /users/{id}
```
Products

```
GET    /products
POST   /products
PUT    /products/{id}
DELETE /products/{id}
```

Orders
```
POST   /orders
GET    /orders/{id}
```
---

## 📐 Boas Práticas Aplicadas

- Separação clara de responsabilidades (SRP)
- Uso de DTO para desacoplamento entre camadas
- Camada de serviço isolando regras de negócio
- Exceptions específicas por domínio
- Organização modular e escalável
