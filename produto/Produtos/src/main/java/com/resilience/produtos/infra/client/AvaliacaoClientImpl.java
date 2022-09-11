package com.resilience.produtos.infra.client;

import com.resilience.produtos.client.avaliacoes.AvaliacaoClient;
import com.resilience.produtos.client.avaliacoes.AvaliacaoModel;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@Slf4j
@Component
public class AvaliacaoClientImpl implements AvaliacaoClient {

    private final RestTemplate restTemplate;

    private final static String API_URL = UriComponentsBuilder
            .fromHttpUrl("http://localhost:8090/avaliacoes")
            .queryParam("produtoId", "{produtoId}")
            .encode()
            .toUriString();

    private final Map<Long, List<AvaliacaoModel>> CACHE = new HashMap<>();

    public AvaliacaoClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @CircuitBreaker(name = "avaliacaoCircuitBreaker", fallbackMethod = "buscarTodosPorProdutoNoCache")
    public List<AvaliacaoModel> buscarTodosPorProduto(Long produtoId) {
        final List<AvaliacaoModel> avaliacoes = executarRequisicao(produtoId);
        return avaliacoes;
    }

    private List<AvaliacaoModel> executarRequisicao(Long produtoId) {
        final Map<String, Object> parametros = new HashMap<>();
        parametros.put("produtoId", produtoId);

        log.info("Buscando avaliações");
        final AvaliacaoModel[] avaliacoes;

        try {
            avaliacoes = restTemplate.getForObject(API_URL, AvaliacaoModel[].class, parametros);
        } catch (Exception e) {
            log.error("Erro ao buscar avaliações");
            throw e;
        }

        log.info("Alimentando cache");
        CACHE.put(produtoId, Arrays.asList(avaliacoes));

        return Arrays.asList(avaliacoes);
    }

    private List<AvaliacaoModel> buscarTodosPorProdutoNoCache(Long produtoId, Throwable e) {
        log.info("Buscando no cache");
        return CACHE.getOrDefault(produtoId, new ArrayList<>());
    }
}
