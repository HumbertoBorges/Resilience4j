package com.resilience.produtos.repository;

import com.resilience.produtos.domain.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository {

    void save(Produto produto);
    Optional<Produto> getOne(Long id);
    List<Produto> getAll();
}
