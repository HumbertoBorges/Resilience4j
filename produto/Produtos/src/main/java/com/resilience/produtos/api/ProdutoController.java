package com.resilience.produtos.api;

import com.resilience.produtos.client.avaliacoes.AvaliacaoClient;
import com.resilience.produtos.client.avaliacoes.AvaliacaoModel;
import com.resilience.produtos.domain.Produto;
import com.resilience.produtos.domain.ProdutoModel;
import com.resilience.produtos.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoRepository produtos;
    private final AvaliacaoClient avaliacoes;

    @GetMapping
    public List<ProdutoModel> buscarTodos() {
        return produtos.getAll().stream()
                .map(this::converterProdutoParaModelo)
                .collect(Collectors.toList());
    }

    @GetMapping("/{produtoId}")
    public ProdutoModel buscarPorId(@PathVariable Long produtoId) {
        return produtos.getOne(produtoId)
                .map(this::converterProdutoParaModeloComAvaliacao)
                .orElseThrow(RecursoNaoEncontradoException::new);
    }

    private ProdutoModel converterProdutoParaModelo(Produto produto) {
        return ProdutoModel.of(produto);
    }

    private ProdutoModel converterProdutoParaModeloComAvaliacao(Produto produto) {
        return ProdutoModel.of(produto, buscarAvaliacaoDoProduto(produto.getId()));
    }

    private List<AvaliacaoModel> buscarAvaliacaoDoProduto(Long productId) {
        return avaliacoes.buscarTodosPorProduto(productId);
    }
}
