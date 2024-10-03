package br.com.fiap.gestaoresiduos.repository;

import br.com.fiap.gestaoresiduos.model.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {
}
