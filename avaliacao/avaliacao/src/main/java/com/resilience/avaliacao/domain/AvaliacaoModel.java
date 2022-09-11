package com.resilience.avaliacao.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvaliacaoModel {

    private Long id;
    private Integer nota;
    private String descricao;
    private String nomeAvaliador;

    public static AvaliacaoModel of(Avaliacao avaliacao) {
        return new AvaliacaoModel(avaliacao.getId(), avaliacao.getNota(), avaliacao.getDescricao(), avaliacao.getNomeAvaliador());
    }
}
