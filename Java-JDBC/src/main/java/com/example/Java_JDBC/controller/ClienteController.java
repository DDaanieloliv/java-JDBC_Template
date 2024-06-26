package com.example.Java_JDBC.controller;

import com.example.Java_JDBC.domain.entity.Cliente;
import com.example.Java_JDBC.domain.repositorio.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private Clientes clientes;

    @PostMapping
    public ResponseEntity<Cliente> salvar(@RequestBody Cliente cliente) {
        Cliente clienteSalvo = clientes.salvar(cliente);
        return new ResponseEntity<>(clienteSalvo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Integer id, @RequestBody Cliente cliente) {
        cliente.setId(id);
        Cliente clienteAtualizado = clientes.atualizar(cliente);
        return ResponseEntity.ok(clienteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        clientes.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> obterTodos() {
        List<Cliente> todosClientes = clientes.obterTodos();
        return ResponseEntity.ok(todosClientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obterPorId(@PathVariable Integer id) {
        Cliente cliente = clientes.obterPorId(id);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }
}
