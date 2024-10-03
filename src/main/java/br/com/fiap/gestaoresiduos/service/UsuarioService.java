package br.com.fiap.gestaoresiduos.service;

import br.com.fiap.gestaoresiduos.dto.UsuarioCadastroDto;
import br.com.fiap.gestaoresiduos.dto.UsuarioExibicaoDto;
import br.com.fiap.gestaoresiduos.exception.UsuarioNaoEcontradoException;
import br.com.fiap.gestaoresiduos.model.Usuario;
import br.com.fiap.gestaoresiduos.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioExibicaoDto salvarUsuario(UsuarioCadastroDto usuarioDTO) {
        String senhaCriptografada = new BCryptPasswordEncoder().encode(usuarioDTO.senha());

        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDTO, usuario);
        usuario.setSenha(senhaCriptografada);

        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return new UsuarioExibicaoDto(usuarioSalvo);
    }

    public UsuarioExibicaoDto buscarPorId(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            return new UsuarioExibicaoDto(usuarioOptional.get());
        } else {
            throw new UsuarioNaoEcontradoException("Usuário não encontrado!");
        }
    }

    public UsuarioExibicaoDto atualizarUsuario(Long id, UsuarioCadastroDto usuarioDTO) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEcontradoException("Usuário não encontrado!"));
        BeanUtils.copyProperties(usuarioDTO, usuario, "senha");
        if (usuarioDTO.senha() != null && !usuarioDTO.senha().isEmpty()) {
            usuario.setSenha(new BCryptPasswordEncoder().encode(usuarioDTO.senha()));
        }
        Usuario usuarioAtualizado = usuarioRepository.save(usuario);
        return new UsuarioExibicaoDto(usuarioAtualizado);
    }

    public void deletarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEcontradoException("Usuário não encontrado!"));
        usuarioRepository.delete(usuario);
    }
}
