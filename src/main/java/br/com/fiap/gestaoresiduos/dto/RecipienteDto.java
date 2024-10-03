package br.com.fiap.gestaoresiduos.dto;

import lombok.Data;

@Data
public class RecipienteDto {
    private Long id;
    private String localizacao;
    private int capacidade;
    private int ocupacao;
}
