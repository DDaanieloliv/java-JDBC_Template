# Sobre a Aplicação

Este projeto é uma aplicação Java que realiza operações básicas de CRUD (Create, Read, Update, Delete) na entidade Cliente no banco de dados durante a inicialização. Essas operações são executadas de forma eficiente utilizando o JdbcTemplate, uma parte integrante do Spring JDBC (Java Database Connectivity).

## Tecnologias Utilizadas

- **Spring Boot:** A aplicação é construída usando o framework Spring Boot, que simplifica o desenvolvimento de aplicativos Java.

- **Spring JDBC:** O JdbcTemplate, componente do Spring JDBC, é empregado para facilitar a interação com o banco de dados relacional, tornando as operações de banco de dados mais concisas e legíveis.

## Funcionalidades Principais

- **Inserção de Clientes:** Ao iniciar a aplicação, são adicionados clientes ao banco de dados.

- **Atualização de Clientes:** Os clientes existentes são atualizados com informações adicionais.

- **Consulta de Clientes:** A aplicação busca e exibe clientes com base em critérios específicos, como parte do nome.

- **Exclusão de Clientes (Comentado):** O código para exclusão de clientes está presente, mas está comentado no código-fonte.

## Executando a Aplicação

Certifique-se de ter o ambiente Java configurado. Execute a aplicação usando o seguinte comando:

```bash
./mvnw spring-boot:run
