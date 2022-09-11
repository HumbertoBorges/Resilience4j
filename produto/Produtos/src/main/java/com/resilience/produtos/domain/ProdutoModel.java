package com.resilience.produtos.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.resilience.produtos.client.avaliacoes.AvaliacaoModel;
import lombok.Data;

import java.util.List;

@Data
public class ProdutoModel {

    private Long id;
    private String nome;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<AvaliacaoModel> avaliacoes;

    public ProdutoModel(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public ProdutoModel(Long id, String nome, List<AvaliacaoModel> avaliacoes) {
        this.id = id;
        this.nome = nome;
        this.avaliacoes = avaliacoes;
    }

    public static ProdutoModel of(Produto produto) {
        return new ProdutoModel(produto.getId(),produto.getNome());
    }

    public static ProdutoModel of(Produto produto, List<AvaliacaoModel> avaliacoes) {
        return new ProdutoModel(
                produto.getId(),
                produto.getNome(),
                avaliacoes
        );
    }
}
