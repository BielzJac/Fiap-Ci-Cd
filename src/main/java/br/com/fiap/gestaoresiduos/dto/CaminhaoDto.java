package br.com.fiap.gestaoresiduos.dto;

import lombok.Data;

@Data
public class CaminhaoDto {
    private Long id;
    private String localizacaoAtual;
    private String status;
}
