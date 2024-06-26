package com.example.Java_JDBC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.Java_JDBC.domain.entity.Cliente;
import com.example.Java_JDBC.domain.repositorio.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

//A aplicação que você forneceu realiza operações básicas de CRUD (Create, Read, Update,
// Delete) na entidade Cliente no banco de dados durante a inicialização. Essas operações
// são realizadas utilizando o JdbcTemplate, que é uma parte do Spring JDBC (Java Database
// Connectivity).

@SpringBootApplication
public class JavaJdbcApplication {


	@Bean
	public CommandLineRunner init(@Autowired Clientes clientes) {
		return args -> {
//			Cliente cliente = new Cliente();
//			cliente.setNome("Fulano");
//			clientes.salvar(cliente);

//			Cliente cliente2 = new Cliente("Outro Cliente");
//			clientes.salvar(cliente2);

			System.out.println("Salvando Clientes.");
			clientes.salvar(new Cliente("Fulano"));
			clientes.salvar(new Cliente("Outro Cliente"));

			List<Cliente> todosClientes = clientes.obterTodos();
			todosClientes.forEach(System.out::println);

			System.out.println("Atualizando Clientes.");
			todosClientes.forEach(c -> {
				c.setNome(c.getNome() + " atualizado.");
				clientes.atualizar(c);
			});

			todosClientes = clientes.obterTodos();
			todosClientes.forEach(System.out::println);

			System.out.println("Buscando Clientes.");
			clientes.buscarPorNome("Cli").forEach(System.out::println);

//            System.out.println("Deletando Clientes.");
//            clientes.obterTodos().forEach(c -> {
//                clientes.deletar(c);
//            });

			todosClientes = clientes.obterTodos();
			if (todosClientes.isEmpty()){
				System.out.println("Nenhum cliente encontrado. ");
			}else {
				todosClientes.forEach(System.out::println);
			}
		};
	}


	public static void main(String[] args) {
		SpringApplication.run(JavaJdbcApplication.class, args);
	}



}
//  - '@Bean':
//      Essa anotação é usada para indicar ao Spring que o método init deve ser tratado como
//      uma fábrica de beans e gerar um bean gerenciado pelo Spring.
//
//  - 'public CommandLineRunner init(@Autowired Clientes clientes) {':
//      Este método é anotado com @Bean e retorna um CommandLineRunner. O CommandLineRunner
//      é uma interface funcional do Spring Boot que fornece um método run que será chamado
//      quando a aplicação for inicializada. O parâmetro @Autowired Clientes clientes
//      indica que a instância da classe Clientes deve ser injetada pelo Spring quando o
//      método for chamado.
//
//  - 'args -> { ... }':
//      Este é um lambda expression que implementa o método run da interface
//      CommandLineRunner. O conteúdo deste lambda será executado ao iniciar a aplicação.
//
//
//  Operações de salvamento e impressão de clientes:
//
//  - 'clientes.salvar(new Cliente("Fulano"))' e
//  'clientes.salvar(new Cliente("Outro Cliente"))':
//      Estas linhas estão salvando dois novos clientes no banco de dados usando o método
//      salvar da classe Clientes.
//
//  - 'List<Cliente> todosClientes = clientes.obterTodos();':
//      Chama o método obterTodos da classe Clientes para recuperar todos os clientes do
//      banco de dados e armazena o resultado em uma lista.
//
//  - 'todosClientes.forEach(System.out::println);':
//      Usa um forEach para imprimir cada cliente no console. Neste caso, os clientes obtidos
//      do banco de dados são impressos no console.
//
//
//  Operações de atualização e busca:
//
//  - 'System.out.println("Atualizando Clientes.");':
//      Imprime uma mensagem no console.
//
//  - 'todosClientes.forEach(c -> { c.setNome(c.getNome() + " atualizado.");
//  clientes.atualizar(c); });':
//      Atualiza cada cliente adicionando " atualizado." ao nome e chamando o método
//      atualizar da classe Clientes.
//
//  - 'todosClientes = clientes.obterTodos();':
//      Atualiza a lista de clientes após as operações de atualização.
//
//  - 'todosClientes.forEach(System.out::println);':
//      Imprime os clientes atualizados no console.
//
//  - 'System.out.println("Buscando Clientes.");':
//      Imprime uma mensagem no console.
//
//  - 'clientes.buscarPorNome("Cli").forEach(System.out::println);':
//  Busca e imprime clientes cujo nome contenha "Cli".
//
//
//  Operações de deleção (comentadas):
//
//  - 'System.out.println("Deletando Clientes."); e
//  clientes.obterTodos().forEach(c -> { clientes.deletar(c); });':
//      Essas linhas estão comentadas, mas se descomentadas, deletariam todos os clientes
//      no banco de dados.
//
//
// Exibição final dos clientes:
//
//  - 'todosClientes = clientes.obterTodos();':
//      Atualiza a lista de clientes.
//
//  - 'if (todosClientes.isEmpty())
//  { System.out.println("Nenhum cliente encontrado. ");
//  } else { todosClientes.forEach(System.out::println); }':
//      Verifica se a lista de clientes está vazia e imprime apropriadamente no console.
//
//
// Em resumo, o método init tem a responsabilidade de inicializar alguns dados no banco de
// dados durante o startup da aplicação, adicionando dois clientes, realizando operações de
// atualização, busca e, opcionalmente, deleção de clientes. No final, exibe os resultados
// no console.