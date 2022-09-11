package com.resilience.produtos.client.avaliacoes;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AvaliacaoModel {

    private Long id;
    private Integer nota;
    private String descricao;
    private String nomeAvaliador;
}
