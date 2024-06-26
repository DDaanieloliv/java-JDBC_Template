package com.example.Java_JDBC.controller;

import com.example.Java_JDBC.domain.entity.ItemPedido;
import com.example.Java_JDBC.domain.repositorio.ItensPedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/itens-pedido")
public class ItemPedidoController {

    @Autowired
    private ItensPedidos itensPedido;

    @PostMapping
    public ResponseEntity<ItemPedido> salvar(@RequestBody ItemPedido itemPedido) {
        ItemPedido itemPedidoSalvo = itensPedido.salvar(itemPedido);
        return new ResponseEntity<>(itemPedidoSalvo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemPedido> atualizar(@PathVariable Integer id, @RequestBody ItemPedido itemPedido) {
        itemPedido.setId(id);
        ItemPedido itemPedidoAtualizado = itensPedido.atualizar(itemPedido);
        return ResponseEntity.ok(itemPedidoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        itensPedido.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ItemPedido>> obterTodos() {
        List<ItemPedido> todosItensPedido = itensPedido.obterTodos();
        return ResponseEntity.ok(todosItensPedido);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemPedido> obterPorId(@PathVariable Integer id) {
        ItemPedido itemPedido = itensPedido.obterPorId(id);
        if (itemPedido == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(itemPedido);
    }

    @GetMapping("/pedido/{pedidoId}")
    public ResponseEntity<List<ItemPedido>> buscarPorPedidoId(@PathVariable Integer pedidoId) {
        List<ItemPedido> itensDoPedido = itensPedido.buscarPorPedidoId(pedidoId);
        return ResponseEntity.ok(itensDoPedido);
    }
}
