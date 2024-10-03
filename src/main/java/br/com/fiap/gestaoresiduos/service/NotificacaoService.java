package br.com.fiap.gestaoresiduos.service;

import br.com.fiap.gestaoresiduos.model.Notificacao;
import br.com.fiap.gestaoresiduos.repository.NotificacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificacaoService {

    @Autowired
    private NotificacaoRepository notificacaoRepository;

    public List<Notificacao> getAllNotificacoes() {
        return notificacaoRepository.findAll();
    }

    public Notificacao saveNotificacao(Notificacao notificacao) {
        return notificacaoRepository.save(notificacao);
    }

    public Notificacao getNotificacaoById(Long id) {
        return notificacaoRepository.findById(id).orElse(null);
    }

    public Notificacao updateNotificacao(Long id, Notificacao notificacao) {
        Notificacao existingNotificacao = notificacaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Notificação não encontrada"));
        existingNotificacao.setMensagem(notificacao.getMensagem());
        existingNotificacao.setData(notificacao.getData());
        existingNotificacao.setUsuario(notificacao.getUsuario());
        return notificacaoRepository.save(existingNotificacao);
    }

    public void deleteNotificacao(Long id) {
        notificacaoRepository.deleteById(id);
    }
}
