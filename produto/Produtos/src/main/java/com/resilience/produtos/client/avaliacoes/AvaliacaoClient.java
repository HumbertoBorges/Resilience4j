package com.resilience.produtos.client.avaliacoes;

import java.util.List;

public interface AvaliacaoClient {
    List<AvaliacaoModel> buscarTodosPorProduto(Long produtoId);
}
