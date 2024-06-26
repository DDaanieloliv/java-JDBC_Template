package com.example.Java_JDBC.domain.repositorio;

import com.example.Java_JDBC.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Clientes {

    private static String INSERT = "insert into cliente (nome) values(?) ";
    private static String SELECT_ALL = "SELECT * FROM CLIENTE ";
    private static String UPDATE = "update cliente set nome = ? where id = ? ";
    private static String DELETE = "delete from cliente where id = ? ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Cliente salvar(Cliente cliente) {
        jdbcTemplate.update( INSERT, new Object[]{cliente.getNome()} );
        return cliente;
    }
    //  -   Este método recebe um objeto Cliente como parâmetro.
    //
    //  -   Usa o "jdbcTemplate" para executar uma atualização no banco de dados,
    //      especificamente a instrução SQL definida em INSERT.
    //
    //  -   "INSERT" é uma constante que contém a instrução SQL para inserir um novo cliente
    //      na tabela cliente.
    //
    //  -   O método "update" do jdbcTemplate é usado para executar operações SQL de modificação
    //      (como inserções).
    //
    //  -   Retorna o mesmo objeto Cliente que foi passado como parâmetro.

    public Cliente atualizar(Cliente cliente){
        jdbcTemplate.update(UPDATE, new Object[]{cliente.getNome(), cliente.getId()
        });
        return cliente;
    }
    //  -Usa o "jdbcTemplate" para executar uma atualização no banco de dados, especificamente
    //  a instrução SQL definida em UPDATE.

    //  -UPDATE é uma constante que contém a instrução SQL para atualizar um cliente na
    //  tabela cliente com base no id.

    //  -O método update do jdbcTemplate é usado para executar operações SQL de modificação
    //  (como atualizações).


    public void deletar(Cliente cliente) {
        deletar(cliente.getId());
    }
    //  Este método chama o método deletar(Integer id) passando o id do cliente como parâmetro.

//- "public void deletar(Cliente cliente)":
//  Esta é a declaração do método. Assim como a versão anterior do método de exclusão, ele
//  é um método void (não retorna valor) e aceita um parâmetro cliente, que é um objeto da
//  classe Cliente a ser excluído.
//
//- "deletar(cliente.getId());":
//  Este é o corpo do método. Ele chama o método de exclusão anterior deletar(Integer id)
//  com o ID do cliente fornecido como argumento.
//
//- "cliente.getId()":
//  Obtém o ID do objeto Cliente. A classe Cliente provavelmente possui um método getId()
//  que retorna o ID associado a esse cliente.

    public void deletar(Integer id) {
        jdbcTemplate.update(DELETE, new Object[]{id});
    }
    //  -Este método recebe o id do cliente como parâmetro.

    //  -Usa o jdbcTemplate para executar uma atualização no banco de dados, especificamente
    //  a instrução SQL definida em DELETE.

    //  -DELETE é uma constante que contém a instrução SQL para excluir um cliente da tabela
    //  cliente com base no id.

//- "public void deletar(Integer id)":
//  Esta é a declaração do método. Ele é um método void, o que significa que não retorna
//  nenhum valor, e aceita um parâmetro id que representa o identificador do cliente a ser
//  excluído.
//
//- "jdbcTemplate.update(DELETE, new Object[]{id});":
//  Este é o corpo do método.
//
//  - 'jdbcTemplate.update(...)':
//      É usado para executar operações SQL de modificação, como exclusões, atualizações ou
//      inserções.
//
//  - 'DELETE':
//      É a constante que contém a instrução SQL para excluir um cliente. Essa instrução SQL
//      é definida fora deste método na classe Clientes.
//
//  - 'new Object[]{id}':
//      É um array de objetos que fornece os valores dos marcadores de posição na instrução
//      SQL. Neste caso, há um marcador de posição ? na instrução SQL, representando o ID
//      do cliente a ser excluído. O valor real do ID é passado como argumento para o
//      método update.
//
//  Portanto, esse método utiliza o jdbcTemplate para executar a instrução SQL de exclusão
//  definida na constante DELETE, substituindo o marcador de posição ? pelo ID fornecido
//  como argumento. Isso resulta na exclusão do cliente com o ID correspondente no banco
//  de dados. Essa abordagem é comum ao realizar operações de remoção de registros usando
//  o Spring JDBC Template.



    public List<Cliente> buscarPorNome(String nome) {
        return jdbcTemplate.query(
                SELECT_ALL.concat("where nome like ? "),
                new Object[]{"%" + nome + "%"},
                obterClienteMapper());
    }
    //  -Este método recebe um nome como parâmetro.

    //  -Usa o jdbcTemplate para executar uma consulta no banco de dados, especificamente
    //  a instrução SQL definida em SELECT_ALL concatenada com a condição where nome like ?.

    //  -SELECT_ALL é uma constante que contém a instrução SQL para selecionar todos os
    //  registros da tabela cliente.

    //  -O concat("where nome like ? ") adiciona uma condição à consulta para buscar registros
    //  cujo nome contenha o valor passado como parâmetro.

    //  -Utiliza o método query do jdbcTemplate para obter uma lista de clientes mapeados
    //  pelo obterClienteMapper().

//- "public List<Cliente> buscarPorNome(String nome)":
//  Esta é a declaração do método. Ele retorna uma lista de objetos do tipo Cliente e
//  aceita um parâmetro nome que será usado para filtrar os resultados.
//
//- "return jdbcTemplate.query(...);":
//  Este é o corpo do método.
//
//  - 'jdbcTemplate.query(...)':
//      Aqui, o jdbcTemplate é usado para executar uma consulta SQL no banco de dados. O
//      método query é usado para consultas que retornam múltiplos resultados.
//
//  - 'SELECT_ALL.concat("where nome like ? ")':
//      Isso concatena a condição WHERE à consulta SQL existente. A condição WHERE nome
//      like ? filtra os resultados com base no nome fornecido. O caractere % no início e
//      no final de ? é usado para indicar que o nome pode corresponder parcialmente.
//
//  - 'new Object[]{"%" + nome + "%"},':
//      Este é um array de objetos que fornece valores para os marcadores de posição na
//      consulta SQL. Neste caso, o valor fornecido é a string que representa o nome com %
//      no início e no final.
//
//  - 'obterClienteMapper()':
//      É chamado para fornecer um objeto RowMapper específico para a classe Cliente, que
//      será usado para mapear as linhas do resultado da consulta para objetos Java.
//
//  - O resultado da consulta é uma lista de objetos Cliente, e isso é o que o método
//      buscarPorNome retorna.
//
//Resumindo, este método utiliza o jdbcTemplate do Spring para executar uma consulta SQL
// que recupera os registros da tabela cliente filtrados pelo nome fornecido. A condição
// like é usada para permitir correspondências parciais. O método obterClienteMapper é
// usado para mapear as linhas do resultado para objetos Cliente. A lista resultante é
// retornada pelo método. Essa abordagem é comum em operações de leitura de dados em um
// banco de dados usando o Spring JDBC Template.

    public List<Cliente> obterTodos() {

        return jdbcTemplate.query(SELECT_ALL, obterClienteMapper());
    }
    //  -Este método usa o jdbcTemplate para executar uma consulta no banco de dados,
    //  especificamente a instrução SQL definida em SELECT_ALL.

    //  -SELECT_ALL é uma constante que contém a instrução SQL para selecionar todos os
    //  registros da tabela cliente.

    //  -Utiliza o método query do jdbcTemplate para obter uma lista de clientes mapeados
    //  pelo obterClienteMapper().

//- "public List<Cliente> obterTodos()":
//  Esta é a declaração do método. Ele retorna uma lista de objetos do tipo Cliente e é
//  marcado como público para que outros componentes possam chamá-lo.
//
//- "return jdbcTemplate.query(SELECT_ALL, obterClienteMapper());":
//  Este é o corpo do método.
//
//  - 'jdbcTemplate.query(SELECT_ALL, obterClienteMapper())':
//      Aqui, o jdbcTemplate é usado para executar uma consulta SQL no banco de dados.
//      O método query é usado para consultas que retornam múltiplos resultados (neste
//      caso, várias linhas representando clientes).
//
//  - 'SELECT_ALL:
//      Esta é a constante que contém a instrução SQL para selecionar todos os registros
//      da tabela cliente. A constante foi definida anteriormente na classe como private
//      static String SELECT_ALL = "SELECT * FROM CLIENTE ";.
//
//  - 'obterClienteMapper()':
//      É chamado para fornecer um objeto RowMapper específico para a classe Cliente. O
//      RowMapper é responsável por mapear as linhas do resultado da consulta para objetos
//      Java.
//
//  O resultado da consulta é uma lista de objetos Cliente, e isso é o que o método
//  obterTodos retorna.
//
//  Resumindo, este método usa o jdbcTemplate do Spring para executar uma consulta SQL que
//  recupera todos os registros da tabela cliente. O método obterClienteMapper é usado para
//  mapear as linhas do resultado para objetos Cliente. A lista resultante é retornada pelo
//  método. Essa abordagem é comum em operações de leitura de dados em um banco de dados
//  usando Spring JDBC Template..


    public Cliente obterPorId(Integer id) {
        String sql = "SELECT * FROM CLIENTE WHERE ID = ?";
        List<Cliente> clientes = jdbcTemplate.query(sql, new Object[]{id}, new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                return null;
            }
        });
        return clientes.isEmpty() ? null : clientes.get(0);
    }


    private static RowMapper<Cliente> obterClienteMapper() {
        return new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {
                Integer id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                return new Cliente(id, nome);
            }
        };
    }
    //  -Este método privado retorna um RowMapper para mapear as linhas do resultado da
    //  consulta para objetos Cliente.

    //  -Cria uma implementação anônima da interface RowMapper no local, que implementa o
    //  método mapRow para mapear os resultados do ResultSet para objetos Cliente.

    //  Esses métodos são operações básicas de CRUD (Create, Read, Update, Delete) para
    //  interagir com a tabela cliente em um banco de dados usando o Spring JDBC Template.


//- "private static RowMapper<Cliente> obterClienteMapper() {":
//
//  - 'private':
//      Este método é privado e só pode ser acessado dentro da classe onde está declarado.
//
//  - 'static':
//      O método é associado à classe e não a instâncias específicas dela.
//
//  - 'RowMapper<Cliente>':
//      Este método retorna um objeto do tipo RowMapper, que é uma interface fornecida
//      pelo Spring JDBC para mapear linhas de resultados de consultas para objetos Java.
//
//  - 'obterClienteMapper()':
//      Este é o nome do método.
//

//  - 'new RowMapper<Cliente>() { ... }':
//      Cria uma instância anônima da interface RowMapper para a classe Cliente.
//
//  - '@Override':
//      Indica que o método mapRow está substituindo um método na interface RowMapper.
//
//  - 'public Cliente mapRow(ResultSet resultSet, int i) throws SQLException { ... }':
//      Este é o método mapRow que deve ser implementado para mapear as linhas do resultado
//      da consulta.


//  - 'ResultSet resultSet':
//      Representa o conjunto de resultados da consulta, contendo os dados retornados do
//      banco de dados.
//  - 'int i':
//      Índice da linha no conjunto de resultados (pode ser ignorado na implementação).
//
//  - 'resultSet.getInt("id")':
//      Obtém o valor do campo "id" da linha atual no resultado da consulta.
//
//  - 'resultSet.getString("nome")':
//      Obtém o valor do campo "nome" da linha atual no resultado da consulta.
//
//  - 'return new Cliente(id, nome)':
//      Cria um novo objeto Cliente com os valores extraídos do resultado da consulta e
//      retorna esse objeto.

//Resumindo, este método fornece uma implementação específica de RowMapper para a classe
//Cliente. Quando usado em conjunto com o método query do Spring JDBC Template, esse
//RowMapper será responsável por transformar as linhas do resultado da consulta em
//objetos Cliente. O método mapRow define como cada linha (representada pelo ResultSet)
//será mapeada para um objeto Cliente.


}


    //  1. '@Repository':
    //
    //      Anotação que indica que a classe é um componente de repositório, ou seja, uma
    //      classe de acesso a dados.
    //
    //
    //  2. 'JdbcTemplate':
    //
    //      É uma classe fornecida pelo Spring para facilitar a execução de operações no
    //      banco de dados usando JDBC. Neste caso, está sendo usado para realizar a
    //      inserção de clientes no banco de dados.
    //
    //      - '@Autowired':
    //          Essa anotação é usada para indicar ao Spring que ele deve automaticamente
    //          injetar uma instância de JdbcTemplate nesse campo quando a classe Clientes
    //          for criada.
    //
    //      - 'private JdbcTemplate jdbcTemplate;':
    //          Este é um campo (membro) privado da classe Clientes do tipo JdbcTemplate.
    //          O JdbcTemplate é uma classe fornecida pelo Spring que simplifica o uso de
    //          JDBC (Java Database Connectivity) para interagir com bancos de dados
    //          relacionais.
    //
    //       - Dentro do método Clientes.salvar, esse jdbcTemplate é usado para executar uma
    //         operação de inserção no banco de dados, conforme definido pela instrução SQL
    //         na constante INSERT. A injeção de dependência facilita o uso de recursos
    //         como JdbcTemplate sem a necessidade de criar manualmente instâncias desses
    //         objetos. O Spring cuida da criação e configuração dessas dependências.
    //
    //
    //  3. 'INSERT':
    //
    //      Representa a instrução SQL para inserir um novo cliente no banco de dados.
    //
    //      - "private":
    //          Declaração de que o membro é privado, ou seja, só pode ser acessado
    //          dentro da própria classe Clientes.
    //
    //      - "static":
    //          Indica que a variável INSERT é uma variável de classe, pertencendo
    //          à classe Clientes em vez de uma instância específica dela.
    //
    //      - "String":
    //          Tipo da variável, que é uma sequência de caracteres (uma string).
    //
    //      - '= "insert into cliente (nome) values(?) "';':
    //          Essa Atribuição Define o valor inicial da variável INSERT.
    //
    //      - "insert into cliente (nome) values(?) ":
    //          A string SQL que representa uma instrução de inserção.
    //
    //      - "insert into cliente":
    //          Indica que queremos realizar uma operação de inserção na tabela
    //          chamada cliente.
    //
    //      - "(nome)":
    //          Lista de colunas nas quais queremos inserir valores. Neste caso,
    //          apenas a coluna nome.
    //
    //      - "values(?)":
    //          Especifica os valores a serem inseridos. O ? é um marcador de
    //          posição para um valor que será fornecido posteriormente.
    //
    //      -   Resumindo, essa linha de código cria uma constante de classe chamada INSERT
    //          que contém a instrução SQL para inserir um cliente na tabela cliente,
    //          especificamente fornecendo um valor para a coluna nome. O uso de ? como
    //          marcador de posição indica que o valor real será fornecido durante a execução
    //          da consulta.
    //
    //
    //  4. 'salvar(Cliente cliente)':
    //
    //      Método que recebe um objeto Cliente e o salva no banco de dados.
    //
    //      - "public Cliente salvar(Cliente cliente)":
    //          Este é o método salvar da classe Clientes. Ele recebe um objeto do tipo
    //          Cliente como parâmetro e retorna um objeto do mesmo tipo.
    //
    //      - "jdbcTemplate.update(INSERT, new Object[]{cliente.getNome()});":
    //          Aqui, o jdbcTemplate (injetado via @Autowired) é usado para executar uma
    //          atualização no banco de dados. O método update do jdbcTemplate é usado para
    //          executar operações SQL de modificação, como inserções, atualizações ou
    //          exclusões.
    //
    //      - "INSERT":
    //          É a constante definida fora deste método que contém a instrução SQL para a
    //          inserção. Nesse caso, a instrução é "insert into cliente (nome) values(?)",
    //          indicando que estamos inserindo um novo registro na tabela "cliente" com um
    //          valor para o campo "nome".
    //
    //      - "new Object[]{cliente.getNome()}":
    //          É um array de objetos que fornece os valores dos parâmetros na instrução SQL.
    //          Aqui, é passado um array contendo apenas o nome do cliente.
    //
    //      - "return cliente;":
    //          No final, o método retorna o mesmo objeto Cliente que foi passado como
    //          parâmetro. Isso é uma prática comum para indicar que a operação foi
    //          concluída e para fornecer uma referência ao objeto que pode conter
    //          informações adicionais ou ter sido modificado durante o processo de salvamento.
    //
    //
    //      Resumindo, esse método salvar usa o jdbcTemplate para executar uma operação
    //      de inserção no banco de dados usando a instrução SQL definida na constante
    //      INSERT e os valores do cliente. Ele retorna o mesmo objeto Cliente, e geralmente
    //      seria usado para indicar que a operação de salvamento foi concluída com sucesso.
    //
    //      Resumidamente, esses códigos estão configurando e utilizando o Spring Boot para
    //      realizar operações no banco de dados (inserir clientes) durante a inicialização
    //      da aplicação. O uso do CommandLineRunner permite que você execute código
    //      específico ao iniciar a aplicação.