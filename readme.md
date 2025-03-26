# Casais App - Backend (API Spring Boot)

[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.java.com/en/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring&logoColor=6DB33F)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Apache_Maven-C60E0D?style=for-the-badge&logo=apache-maven&logoColor=white)](https://maven.apache.org/)
[![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=json-web-tokens&logoColor=white)](https://jwt.io/)
[![Lombok](https://img.shields.io/badge/Lombok-8DB634?style=for-the-badge&logo=lombok&logoColor=white)](https://projectlombok.org/)

## Visão Geral

Este é o backend do Casais App, construído utilizando o framework Spring Boot em Java. Ele fornece uma API RESTful para o aplicativo mobile, gerenciando dados de usuários, desafios, tarefas e recompensas. A API é responsável pela lógica de negócios, persistência de dados e segurança.

## Funcionalidades Principais da API

* **Autenticação e Autorização:**
    * Registro e login de usuários com Spring Security e JWT (JSON Web Tokens).
    * Proteção de endpoints da API, exigindo autenticação para acesso.
    * Mecanismos de autorização para garantir que os usuários só possam acessar os recursos apropriados.
* **Gerenciamento de Desafios:**
    * Criação, leitura, atualização e exclusão (CRUD) de desafios.
    * Associação de desafios a usuários.
    * Endpoint para listar desafios ativos para um usuário.
* **Gerenciamento de Tarefas:**
    * Criação, leitura, atualização e exclusão (CRUD) de tarefas.
    * Associação de tarefas a desafios e usuários criadores.
    * Endpoint para listar todas as tarefas de um desafio específico.
* **Gerenciamento de Recompensas:**
    * Criação, leitura, atualização e exclusão (CRUD) de recompensas.
    * Associação de recompensas a desafios (potencialmente).
* **Persistência de Dados:**
    * Utilização do Spring Data JPA e Hibernate para interagir com o banco de dados relacional.
* **Arquitetura RESTful:**
    * Design da API seguindo os princípios REST para comunicação eficiente com o frontend.
* **Tratamento de Erros:**
    * Implementação de tratamento de erros consistente e retorno de códigos de status HTTP apropriados.

## Tecnologias Utilizadas

* [![Java](https://img.shields.io/badge/Java-ED8B00?style=flat-square&logo=openjdk&logoColor=white)](https://www.java.com/en/)
* [![Spring Boot](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=flat-square&logo=spring&logoColor=6DB33F)](https://spring.io/projects/spring-boot)
* [![Maven](https://img.shields.io/badge/Apache_Maven-C60E0D?style=flat-square&logo=apache-maven&logoColor=white)](https://maven.apache.org/)
* [![JWT](https://img.shields.io/badge/JWT-black?style=flat-square&logo=json-web-tokens&logoColor=white)](https://jwt.io/)
* [![Lombok](https://img.shields.io/badge/Lombok-8DB634?style=flat-square&logo=lombok&logoColor=white)](https://projectlombok.org/)
* Hibernate
* Spring Security
* Spring Data JPA
* H2 Database (em desenvolvimento/teste)

## Pré-requisitos

* **Java Development Kit (JDK):** Versão compatível com Spring Boot.
* **Maven:** Instalado e configurado para gerenciamento de dependências e build.
* **Banco de Dados:** Um banco de dados relacional configurado (H2 para desenvolvimento, outro para produção).

## Como Executar o Backend

1.  **Clone o repositório do backend:**
    ```bash
    git clone [https://github.com/tallyto/jogo-em-equipe-back](https://github.com/tallyto/jogo-em-equipe-back)
    cd jogo-em-equipe-back
    ```

2.  **Configure o banco de dados:**
    * Verifique as configurações do banco de dados no arquivo `src/main/resources/application.properties` ou `application.yml`.
    * Configure as credenciais de acesso ao seu banco de dados.
    * (Se estiver usando H2, as configurações padrão geralmente são suficientes para desenvolvimento.)

3.  **Execute a aplicação Spring Boot usando Maven:**
    ```bash
    mvn spring-boot:run
    ```

4.  **A API estará disponível em:** `http://localhost:8080` (a porta pode ser configurada no arquivo de propriedades).

## Endpoints da API

Aqui estão alguns dos principais endpoints da API (a lista completa pode variar):

* **`POST /api/auth/signup`:** Registra um novo usuário.
* **`POST /api/auth/login`:** Autentica um usuário e retorna um token JWT.
* **`GET /api/desafios`:** Lista todos os desafios associados ao usuário autenticado. (Requer autenticação)
* **`POST /api/desafios`:** Cria um novo desafio. (Requer autenticação)
* **`GET /api/desafios/{desafioId}`:** Retorna os detalhes de um desafio específico. (Requer autenticação)
* **`POST /api/desafios/{desafioId}/tarefas`:** Cria uma nova tarefa para um desafio. (Requer autenticação)
* **`GET /api/desafios/{desafioId}/tarefas`:** Lista todas as tarefas de um desafio específico. (Requer autenticação)
* **`POST /api/recompensas`:** Cria uma nova recompensa. (Requer autenticação)
* **`GET /api/recompensas`:** Lista todas as recompensas. (Requer autenticação)
* **`POST /api/desafios/{desafioId}/usuarios/{usuarioId}`:** Adiciona um usuário a um desafio. (Requer autenticação)


## Próximos Passos e Melhorias

* Implementar a lógica completa para o gerenciamento de recompensas (resgate, associação a usuários).
* Adicionar endpoints para atualizar e excluir desafios, tarefas e recompensas.
* Implementar validações robustas para os dados de entrada.
* Adicionar tratamento de erros mais detalhado e específico.
* Implementar testes unitários e de integração para todas as camadas da aplicação.
* Configurar um banco de dados de produção (PostgreSQL, MySQL, etc.).
* Implementar logs para monitoramento da aplicação.
* Considerar a implementação de versionamento da API.

## Contribuição

Contribuições são bem-vindas! Se você encontrar algum problema ou tiver sugestões de melhorias, por favor, abra uma issue ou envie um pull request no repositório.
