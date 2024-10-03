package br.com.fiap.gestaoresiduos.controller;

import br.com.fiap.gestaoresiduos.dto.NotificacaoDto;
import br.com.fiap.gestaoresiduos.model.Notificacao;
import br.com.fiap.gestaoresiduos.service.NotificacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notificacoes")
public class NotificacaoController {

    @Autowired
    private NotificacaoService notificacaoService;

    @GetMapping("/todos")
    @ResponseStatus(HttpStatus.OK)
    public List<NotificacaoDto> getAllNotificacoes() {
        return notificacaoService.getAllNotificacoes().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/adicionar")
    @ResponseStatus(HttpStatus.CREATED)
    public NotificacaoDto saveNotificacao(@RequestBody NotificacaoDto notificacaoDto) {
        Notificacao notificacao = convertToEntity(notificacaoDto);
        return convertToDto(notificacaoService.saveNotificacao(notificacao));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public NotificacaoDto getNotificacaoById(@PathVariable Long id) {
        return convertToDto(notificacaoService.getNotificacaoById(id));
    }

    @PutMapping("/atualizar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public NotificacaoDto updateNotificacao(@PathVariable Long id, @RequestBody NotificacaoDto notificacaoDto) {
        Notificacao notificacao = convertToEntity(notificacaoDto);
        return convertToDto(notificacaoService.updateNotificacao(id, notificacao));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNotificacao(@PathVariable Long id) {
        notificacaoService.deleteNotificacao(id);
    }

    private NotificacaoDto convertToDto(Notificacao notificacao) {
        NotificacaoDto notificacaoDto = new NotificacaoDto();
        notificacaoDto.setId(notificacao.getId());
        notificacaoDto.setMensagem(notificacao.getMensagem());
        notificacaoDto.setData(notificacao.getData());
        notificacaoDto.setUsuario(notificacao.getUsuario());
        return notificacaoDto;
    }

    private Notificacao convertToEntity(NotificacaoDto notificacaoDto) {
        Notificacao notificacao = new Notificacao();
        notificacao.setMensagem(notificacaoDto.getMensagem());
        notificacao.setData(notificacaoDto.getData());
        notificacao.setUsuario(notificacaoDto.getUsuario());
        return notificacao;
    }
}
