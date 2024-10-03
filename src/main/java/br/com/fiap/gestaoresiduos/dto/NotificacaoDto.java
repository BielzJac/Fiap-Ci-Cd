package br.com.fiap.gestaoresiduos.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class NotificacaoDto {
    private Long id;
    private String mensagem;
    private LocalDate data;
    private String usuario;
}
