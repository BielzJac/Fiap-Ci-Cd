package br.com.fiap.gestaoresiduos.repository;

import br.com.fiap.gestaoresiduos.model.Caminhao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaminhaoRepository extends JpaRepository<Caminhao, Long> {
}
