package com.example.Java_JDBC.controller;

import com.example.Java_JDBC.domain.entity.Pedido;
import com.example.Java_JDBC.domain.repositorio.Pedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private Pedidos pedidos;

    @PostMapping
    public ResponseEntity<Pedido> salvar(@RequestBody Pedido pedido) {
        Pedido pedidoSalvo = pedidos.salvar(pedido);
        return new ResponseEntity<>(pedidoSalvo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizar(@PathVariable Integer id, @RequestBody Pedido pedido) {
        pedido.setId(id);
        Pedido pedidoAtualizado = pedidos.atualizar(pedido);
        return ResponseEntity.ok(pedidoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        pedidos.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> obterTodos() {
        List<Pedido> todosPedidos = pedidos.obterTodos();
        return ResponseEntity.ok(todosPedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obterPorId(@PathVariable Integer id) {
        Pedido pedido = pedidos.obterPorId(id);
        if (pedido == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedido);
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Pedido>> buscarPorClienteId(@PathVariable Integer clienteId) {
        List<Pedido> pedidosDoCliente = pedidos.buscarPorClienteId(clienteId);
        return ResponseEntity.ok(pedidosDoCliente);
    }
}
