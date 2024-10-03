package br.com.fiap.gestaoresiduos.controller;

import br.com.fiap.gestaoresiduos.config.security.TokenService;
import br.com.fiap.gestaoresiduos.dto.LoginDto;
import br.com.fiap.gestaoresiduos.dto.TokenDto;
import br.com.fiap.gestaoresiduos.dto.UsuarioCadastroDto;
import br.com.fiap.gestaoresiduos.model.Usuario;
import br.com.fiap.gestaoresiduos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@RequestBody UsuarioCadastroDto usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTO.nome());
        usuario.setEmail(usuarioDTO.email());
        usuario.setSenha(passwordEncoder.encode(usuarioDTO.senha()));
        usuario.setRole(usuarioDTO.role());
        usuarioRepository.save(usuario);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public TokenDto loginUser(@RequestBody LoginDto loginDto) {
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.senha());
        Authentication authentication = authenticationManager.authenticate(login);
        String token = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        return new TokenDto(token);
    }

}


