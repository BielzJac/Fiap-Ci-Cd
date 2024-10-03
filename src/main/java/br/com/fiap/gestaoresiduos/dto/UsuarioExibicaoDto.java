package br.com.fiap.gestaoresiduos.dto;

import br.com.fiap.gestaoresiduos.model.Usuario;

public record UsuarioExibicaoDto(
        Long usuarioId,
        String nome,
        String email,
        String role
) {
    public UsuarioExibicaoDto(Usuario usuario) {
        this(usuario.getUsuarioId(), usuario.getNome(), usuario.getEmail(), usuario.getRole().name());
    }
}
