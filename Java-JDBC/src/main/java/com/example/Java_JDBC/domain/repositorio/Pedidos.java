package com.example.Java_JDBC.domain.repositorio;


import com.example.Java_JDBC.domain.entity.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository
public class Pedidos {

    private static final String INSERT = "insert into pedido (cliente_id, data_pedido, total) values(?, ?, ?) ";
    private static final String SELECT_ALL = "SELECT * FROM pedido ";
    private static final String UPDATE = "update pedido set cliente_id = ?, data_pedido = ?, total = ? where id = ? ";
    private static final String DELETE = "delete from pedido where id = ? ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Pedido salvar(Pedido pedido) {
        jdbcTemplate.update(INSERT, pedido.getCliente(), pedido.getData_pedido(), pedido.getTotal());
        return pedido;
    }

    public Pedido atualizar(Pedido pedido) {
        jdbcTemplate.update(UPDATE, pedido.getCliente(), pedido.getData_pedido(), pedido.getTotal(), pedido.getId());
        return pedido;
    }

    public void deletar(Pedido pedido) {
        deletar(pedido.getId());
    }

    public void deletar(Integer id) {
        jdbcTemplate.update(DELETE, id);
    }

    public List<Pedido> obterTodos() {
        return jdbcTemplate.query(SELECT_ALL, obterPedidoMapper());
    }

    public List<Pedido> buscarPorClienteId(Integer clienteId) {
        return jdbcTemplate.query(
                SELECT_ALL.concat("where cliente_id = ? "),
                new Object[]{clienteId},
                obterPedidoMapper());
    }


    public Pedido obterPorId(Integer id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM PEDIDO WHERE ID = ?",
                new Object[]{id},
                obterPedidoMapper()
        );
    }

    private static RowMapper<Pedido> obterPedidoMapper() {
        return new RowMapper<Pedido>() {
            @Override
            public Pedido mapRow(ResultSet resultSet, int i) throws SQLException {
                Integer id = resultSet.getInt("id");
                Integer clienteId = resultSet.getInt("cliente_id");
                Date dataPedido = resultSet.getDate("data_pedido");
                BigDecimal total = resultSet.getBigDecimal("total");
                return new Pedido(id, clienteId, dataPedido, total);
            }
        };
    }
}
