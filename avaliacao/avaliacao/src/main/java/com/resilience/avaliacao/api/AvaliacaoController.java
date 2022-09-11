package com.resilience.avaliacao.api;

import com.resilience.avaliacao.domain.AvaliacaoModel;
import com.resilience.avaliacao.repository.AvaliacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    private final AvaliacaoRepository repository;

    @GetMapping
    public List<AvaliacaoModel> buscarPorProduto(@RequestParam Long produtoId) {
        return repository.getAll().stream()
                .filter(avaliacao -> avaliacao.getProdutoId().equals(produtoId))
                .map(AvaliacaoModel::of)
                .collect(Collectors.toList());
    }
}
