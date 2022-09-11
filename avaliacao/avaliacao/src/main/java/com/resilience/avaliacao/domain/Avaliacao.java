package com.resilience.avaliacao.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Avaliacao {

    private Long id;
    private Integer nota;
    private String descricao;
    private String nomeAvaliador;
    private Long produtoId;

}
