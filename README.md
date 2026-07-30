# Task Manager API 📝

API REST para gerenciamento de tarefas (Task Manager) desenvolvida durante o laboratório **"Criando sua primeira API REST"** da plataforma **DIO (Digital Innovation One)**.

> ℹ️ **Nota de Implementação:** O curso foi ministrado originalmente em **Java**, porém este repositório traz a implementação adaptada e construída em **Kotlin**.

---

## 🏗️ Arquitetura do Projeto

O projeto segue os princípios de **Domain-Driven Design (DDD)** e **Clean Architecture**, sendo organizado nos seguintes pacotes principais:

- **`domain`**: Coração da aplicação. Contém as entidades de negócio puras, objetos de valor e os contratos (interfaces) dos repositórios, sem dependências de frameworks externos.
- **`application`**: Camada de casos de uso (Use Cases) e regras de negócio da aplicação, orquestrando as entidades do domínio para realizar as operações.
- **`infrastructure`**: Detalhes técnicos e elementos externos, como controllers REST, adaptadores de banco de dados e configurações do Spring.

---

## 🛠️ Tecnologias Utilizadas

- **Kotlin** (Linguagem principal)
- **Spring Boot** (Framework para API REST)
- **Mockito** (Testes unitários e mocks)
