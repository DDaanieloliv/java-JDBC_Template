package com.example.Java_JDBC.domain.repositorio;

import com.example.Java_JDBC.domain.entity.ItemPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ItensPedidos {

    private static final String INSERT = "insert into item_pedido (pedido_id, produto_id, quantidade) values(?, ?, ?) ";
    private static final String SELECT_ALL = "SELECT * FROM item_pedido ";
    private static final String UPDATE = "update item_pedido set pedido_id = ?, produto_id = ?, quantidade = ? where id = ? ";
    private static final String DELETE = "delete from item_pedido where id = ? ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ItemPedido salvar(ItemPedido itemPedido) {
        jdbcTemplate.update(INSERT, itemPedido.getPedido(), itemPedido.getProduto(), itemPedido.getQuantidade());
        return itemPedido;
    }

    public ItemPedido atualizar(ItemPedido itemPedido) {
        jdbcTemplate.update(UPDATE, itemPedido.getPedido(), itemPedido.getProduto(), itemPedido.getQuantidade(), itemPedido.getId());
        return itemPedido;
    }

    public void deletar(ItemPedido itemPedido) {
        deletar(itemPedido.getId());
    }

    public void deletar(Integer id) {
        jdbcTemplate.update(DELETE, id);
    }

    public List<ItemPedido> obterTodos() {
        return jdbcTemplate.query(SELECT_ALL, obterItemPedidoMapper());
    }

    public List<ItemPedido> buscarPorPedidoId(Integer pedidoId) {
        return jdbcTemplate.query(
                SELECT_ALL.concat("where pedido_id = ? "),
                new Object[]{pedidoId},
                obterItemPedidoMapper());
    }

    private static RowMapper<ItemPedido> obterItemPedidoMapper() {
        return new RowMapper<ItemPedido>() {
            @Override
            public ItemPedido mapRow(ResultSet resultSet, int i) throws SQLException {
                Integer id = resultSet.getInt("id");
                Integer pedidoId = resultSet.getInt("pedido_id");
                Integer produtoId = resultSet.getInt("produto_id");
                Integer quantidade = resultSet.getInt("quantidade");
                return new ItemPedido(id, pedidoId, produtoId, quantidade);
            }
        };
    }

    public ItemPedido obterPorId(Integer id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM ITEM_PEDIDO WHERE ID = ?",
                new Object[]{id},
                obterItemPedidoMapper()
        );
    }


}
