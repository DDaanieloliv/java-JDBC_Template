package com.example.Java_JDBC.domain.entity;

import java.math.BigDecimal;

public class Produto {

    private Integer id;
    private BigDecimal preco;


    private String descricao;

    public Produto(Integer id, String descricao, BigDecimal preco) {

    }

    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
