package br.com.fiap.gestaoresiduos.controller;

import br.com.fiap.gestaoresiduos.dto.UsuarioCadastroDto;
import br.com.fiap.gestaoresiduos.dto.UsuarioExibicaoDto;
import br.com.fiap.gestaoresiduos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioExibicaoDto buscarPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id);
    }

    @PutMapping("/atualizar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioExibicaoDto atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioCadastroDto usuarioDTO) {
        return usuarioService.atualizarUsuario(id, usuarioDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarUsuario(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);
    }
}
