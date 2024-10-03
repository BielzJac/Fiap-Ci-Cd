package br.com.fiap.gestaoresiduos.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ColetaDto {
    private Long id;
    private Long caminhaoId;
    private Long recipienteId;
    private LocalDate data;
}
