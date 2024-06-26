package com.example.Java_JDBC.domain.repositorio;


import com.example.Java_JDBC.domain.entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Produtos {

    private static final String INSERT = "insert into produto (descricao, preco) values(?, ?) ";
    private static final String SELECT_ALL = "SELECT * FROM produto ";
    private static final String UPDATE = "update produto set descricao = ?, preco = ? where id = ? ";
    private static final String DELETE = "delete from produto where id = ? ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Produto salvar(Produto produto) {
        jdbcTemplate.update(INSERT, produto.getDescricao(), produto.getPreco());
        return produto;
    }

    public Produto atualizar(Produto produto) {
        jdbcTemplate.update(UPDATE, produto.getDescricao(), produto.getPreco(), produto.getId());
        return produto;
    }

    public void deletar(Produto produto) {
        deletar(produto.getId());
    }

    public void deletar(Integer id) {
        jdbcTemplate.update(DELETE, id);
    }

    public List<Produto> obterTodos() {
        return jdbcTemplate.query(SELECT_ALL, obterProdutoMapper());
    }

    public List<Produto> buscarPorDescricao(String descricao) {
        return jdbcTemplate.query(
                SELECT_ALL.concat("where descricao like ? "),
                new Object[]{"%" + descricao + "%"},
                obterProdutoMapper());
    }

    private static RowMapper<Produto> obterProdutoMapper() {
        return new RowMapper<Produto>() {
            @Override
            public Produto mapRow(ResultSet resultSet, int i) throws SQLException {
                Integer id = resultSet.getInt("id");
                String descricao = resultSet.getString("descricao");
                BigDecimal preco = resultSet.getBigDecimal("preco");
                return new Produto(id, descricao, preco);
            }
        };
    }

    public Produto obterPorId(Integer id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM PRODUTO WHERE ID = ?",
                new Object[]{id},
                obterProdutoMapper()
        );
    }
}
